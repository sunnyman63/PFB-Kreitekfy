package com.kreitek.kreitekfy.song.application.services.impl;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.mapper.SongMapper;
import com.kreitek.kreitekfy.song.application.services.SongService;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.song.domain.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository repository;
    private final SongMapper mapper;

    @Autowired
    public SongServiceImpl(SongRepository repository, SongMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
   
    @Override
    public Optional<SongDTO> getSongById(Long idSong) {
        return this.repository
                .findById(idSong)
                .map(mapper::toDto);
    }

   // @Override
    //public List<SongDTO> getNewestSongs() {
    //  List<Song> songDTOs = this.repository.getAllSongsByOrderByInclusion_DateDesc();
      //  List<Song> newests = new ArrayList<>();
      //  return null;
   // }

    @Override
    public Page<SongDTO> getSongByCriteriaPaged(Pageable pageable, String filter) {
        Page<Song> itemPage = this.repository.findAll(pageable, filter);
        return itemPage.map(mapper::toDto);
    }

    @Override
    public SongDTO saveSong(SongDTO songDTO) {
        songDTO.setTotalViews(0L);
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        songDTO.setInclusionDate(formateador.format(new Date()));
        Song song = this.mapper.toEntity(songDTO);
        song = this.repository.save(song);
        return this.mapper.toDto(song);
    }

    @Override
    public void deleteSong(Long idSong) {
        this.repository.deleteById(idSong);
    }


}
