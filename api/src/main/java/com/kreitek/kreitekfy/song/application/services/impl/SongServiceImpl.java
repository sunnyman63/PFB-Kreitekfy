package com.kreitek.kreitekfy.song.application.services.impl;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;
import com.kreitek.kreitekfy.song.application.mapper.SongMapper;
import com.kreitek.kreitekfy.song.application.services.SongService;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.song.domain.repository.SongRepository;
import com.kreitek.kreitekfy.style.infraestructure.persistence.StyleRepository;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.application.service.UserSongService;
import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;
import com.kreitek.kreitekfy.userSong.infraestructure.persistence.UserSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository repository;
    private final SongMapper mapper;
    private final UserSongService userSongService;
    private final UserSongRepository userSongRepository;
    private final StyleRepository styleRepository;

    @Autowired
    public SongServiceImpl(SongRepository repository, SongMapper mapper, UserSongService userSongService, UserSongRepository userSongRepository, StyleRepository styleRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userSongService = userSongService;
        this.userSongRepository = userSongRepository;
        this.styleRepository = styleRepository;
    }
   

    @Override
    public Optional<SongDTO> getSongById(Long idSong) {
        Optional<Song> opSong = this.repository.findById(idSong);
        if(opSong.isPresent()) {
            List<Song> song = new ArrayList<>();
            song.add(opSong.get());
            song = this.addCalculatedValuesToSong(song);
            opSong = Optional.of(song.get(0));
        }
        return opSong.map(mapper::toDto);
    }

    @Override
    public List<SongSimpleDTO> getSongs() {
        List<Song> songs = this.addCalculatedValuesToSong(this.repository.findAll());
        return this.mapper.toSimpleDto(songs);
    }

    @Override
    public List<SongSimpleDTO> getSongsByName(String partialName) {
        List<Song> songs = repository.getSongsByName(partialName);
        return this.mapper.toSimpleDto(songs);
    }

    public List<SongDTO> getAllSongsByOrderByInclusionDateDesc() {
        List<Song> newestSong = this.addCalculatedValuesToSong(this.repository.findAll());
        newestSong.sort(Comparator.comparing(Song::getInclusionDate).reversed());

        List<Song> fiveNewestSongs = new ArrayList<>();
        for (int i = 0; i < newestSong.size(); i++){
            fiveNewestSongs.add(newestSong.get(i));
            if(i == 4) {
                break;
            }
        }
        return this.mapper.toDto(fiveNewestSongs);
    }

    @Override
    public List<SongDTO> findByOrderByTotalRateDesc() {
        List<Song> calculatedAdded = this.addCalculatedValuesToSong(this.repository.findAll());
        calculatedAdded.sort(Comparator.comparing(Song::getTotalRate).reversed());
        List<Song> fiveBestRatedSongs = new ArrayList<>();
        for (int i = 0; i < calculatedAdded.size(); i++){
            fiveBestRatedSongs.add(calculatedAdded.get(i));
            if(i == 4) {
                break;
            }
        }

        return this.mapper.toDto(fiveBestRatedSongs);
    }

    @Override
    public List<SongDTO> findByOrderByTotalViewsDesc() {
        List<Song> calculatedAdded = this.addCalculatedValuesToSong(this.repository.findAll());
        calculatedAdded.sort(Comparator.comparing(Song::getTotalViews).reversed());
        List<Song> fiveMostViewedSongs = new ArrayList<>();
        for (int i = 0; i < calculatedAdded.size(); i++){
            fiveMostViewedSongs.add(calculatedAdded.get(i));
            if(i == 4) {
                break;
            }
        }
        return this.mapper.toDto(fiveMostViewedSongs);
    }

    @Override
    public List<SongDTO> findByUserPreferences(Long idUser) {

        List<UserSong> userSongs = this.userSongRepository.getUserSongByUser_Id(idUser);
        Long mostlistened;
        Long secondListened=0L;

        HashMap<Long,Long> StylesTimes = getStylesByListened(userSongs);

        mostlistened=getMostListenedStyle(StylesTimes);
        StylesTimes.remove(mostlistened);
        if(StylesTimes.size()==0){
            return getAllSongsByStyleandRate(mostlistened, secondListened);
        }
        secondListened=getMostListenedStyle(StylesTimes);

        System.out.println(mostlistened);
        System.out.println(secondListened);

        return getAllSongsByStyleandRate(mostlistened, secondListened);

    }

    private List<SongDTO> getAllSongsByStyleandRate(Long mostlistened, Long secondListened) {

        List<Song> totalSongs = new ArrayList<Song>(this.repository.getSongsByStyle(mostlistened));
        totalSongs.addAll(this.repository.getSongsByStyle(secondListened));

        List<Song> mostratedListened = this.addCalculatedValuesToSong(totalSongs);
        mostratedListened.sort(Comparator.comparing(Song::getTotalRate).reversed());
        List<Song> fiveBestRatedSongs = new ArrayList<>();
        for (int i = 0; i < mostratedListened.size() && mostratedListened.get(i).getTotalRate()>=3 ; i++){
            fiveBestRatedSongs.add(mostratedListened.get(i));
            if(i == 4) {
                break;
            }
        }
        return this.mapper.toDto(fiveBestRatedSongs);
    }

    public HashMap<Long,Long> getStylesByListened(List<UserSong> userSongs){
        HashMap<Long,Long> StylesTimes= new HashMap<Long,Long>();

        for(int i = 0; i<userSongs.size();i++){

            if(StylesTimes.containsKey(userSongs.get(i).getSong().getStyle().getId())){
                Long aux=0L;
                aux=StylesTimes.get(userSongs.get(i).getSong().getStyle().getId());

                StylesTimes.put(userSongs.get(i).getSong().getStyle().getId(), userSongs.get(i).getPersonalViews()+aux);
            }else
                StylesTimes.put(userSongs.get(i).getSong().getStyle().getId(), userSongs.get(i).getPersonalViews());
        }
        return StylesTimes;
    }

    public Long getMostListenedStyle( HashMap<Long,Long> StylesTimes){
        Long mostListenedStyle=0L;

        Long maxValueInMap=(Collections.max(StylesTimes.values()));
        for (Map.Entry<Long, Long> entry : StylesTimes.entrySet()) {
            if (entry.getValue()==maxValueInMap) {
                mostListenedStyle=entry.getKey();
            }
        }
        return mostListenedStyle;
    }



    @Override
    public Page<SongDTO> getSongByCriteriaPaged(Pageable pageable, String filter) {
        Page<Song> itemPage = this.repository.findAll(pageable, filter);
        List<Song> calculatedAdded = this.addCalculatedValuesToSong(itemPage.getContent());
        itemPage = new PageImpl<Song>(calculatedAdded, itemPage.getPageable(), calculatedAdded.size());
        return itemPage.map(mapper::toDto);
    }

    @Override
    public List<Song> addCalculatedValuesToSong(List<Song> iterable) {
        iterable.forEach(song -> {
            AtomicReference<Long> totalRate = new AtomicReference<>(0L);
            AtomicReference<Long> totalViews = new AtomicReference<>(0L);
            AtomicInteger numRates = new AtomicInteger();
            List<UserSongDTO> userSongs = userSongService.getAllUserSongBySong_Id(song.getId());
            userSongs.forEach(userSongDTO -> {
                totalViews.updateAndGet(v -> v + userSongDTO.getPersonalViews());
                if(userSongDTO.getPersonalValorations() != null) {
                    totalRate.updateAndGet(v -> v + userSongDTO.getPersonalValorations());
                    numRates.getAndIncrement();
                }

            });
            song.setTotalViews(totalViews.get());
            if(numRates.get() != 0) {
                song.setTotalRate(totalRate.get() / numRates.get());
            } else {
                song.setTotalRate(totalRate.get());
            }
        });
        return iterable;
    }

    @Override
    public SongDTO saveSong(SongDTO songDTO) {
        songDTO.setTotalViews(0L);
        songDTO.setTotalViews(0L);
        songDTO.setInclusionDate(new Date());
        Song song = this.mapper.toEntity(songDTO);
        song = this.repository.save(song);
        return this.mapper.toDto(song);
    }

    @Override
    public void deleteSong(Long idSong) {
        this.repository.deleteById(idSong);
    }


}
