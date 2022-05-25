package com.kreitek.kreitekfy.song.application.services.impl;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.mapper.SongMapper;
import com.kreitek.kreitekfy.song.application.services.SongService;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.song.domain.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<SongDTO> getAllSongs() {
        List<Song> songs = this.repository.findAll();
        return this.mapper.toDto(songs);
    }

    @Override
    public Optional<SongDTO> getSongById(Long idSong) {
        return this.repository
                .findById(idSong)
                .map(mapper::toDto);
    }

    @Override
    public List<SongDTO> getNewestSongs() {
//        List<Song> songDTOs = this.repository.getAllSongsByOrderByInclusion_DateDesc();
//        List<Song> newests = new ArrayList<>();
        return null;
    }

    @Override
    public SongDTO saveSong(SongDTO songDTO) {
        songDTO.setTotalVisualizations(0L);
        Song song = this.mapper.toEntity(songDTO);
        song = this.repository.save(song);
        return this.mapper.toDto(song);
    }

    @Override
    public void deleteSong(Long idSong) {
        this.repository.deleteById(idSong);
    }


}
