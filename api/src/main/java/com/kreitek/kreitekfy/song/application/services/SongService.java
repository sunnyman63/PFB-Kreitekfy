package com.kreitek.kreitekfy.song.application.services;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongService {

    Optional<SongDTO> getSongById(Long idSong);
    SongDTO saveSong(SongDTO songDTO);
    void deleteSong(Long idSong);
    List<SongDTO> getNewestSongs();

    Page<SongDTO> getSongByCriteriaPaged(Pageable pageable, String filter);
}
