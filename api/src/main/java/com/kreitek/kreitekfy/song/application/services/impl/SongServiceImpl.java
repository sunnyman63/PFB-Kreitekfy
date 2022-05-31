package com.kreitek.kreitekfy.song.application.services.impl;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;
import com.kreitek.kreitekfy.song.application.mapper.SongMapper;
import com.kreitek.kreitekfy.song.application.services.SongService;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.song.domain.repository.SongRepository;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.application.service.UserSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository repository;
    private final SongMapper mapper;
    private final UserSongService userSongService;

    @Autowired
    public SongServiceImpl(SongRepository repository, SongMapper mapper, UserSongService userSongService) {
        this.repository = repository;
        this.mapper = mapper;
        this.userSongService = userSongService;
    }
   

    @Override
    public Optional<SongDTO> getSongById(Long idSong) {
        return this.repository
                .findById(idSong)
                .map(mapper::toDto);
    }

    @Override
    public List<SongSimpleDTO> getSongs() {
        List<Song> songs = repository.findAll();
        return this.mapper.toSimpleDto(songs);
    }

    @Override
    public List<SongSimpleDTO> getSongsByName(String partialName) {
        List<Song> songs = repository.getSongsByName(partialName);
        return this.mapper.toSimpleDto(songs);
    }

    @Override
    public List<SongDTO> getNewestSongs() {
//        List<Song> songDTOs = this.repository.getAllSongsByOrderByInclusion_DateDesc();
//        List<Song> newests = new ArrayList<>();
        return null;
    }

    @Override
    public Page<SongDTO> getSongByCriteriaPaged(Pageable pageable, String filter) {
        Page<Song> itemPage = this.repository.findAll(pageable, filter);
        List<Song> calculatedAdded = this.addCalculatedValuesToSong(itemPage.getContent());
        itemPage = new PageImpl<Song>(calculatedAdded, itemPage.getPageable(), calculatedAdded.size());
        return itemPage.map(mapper::toDto);

    }

    private List<Song> addCalculatedValuesToSong(List<Song> iterable) {
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
