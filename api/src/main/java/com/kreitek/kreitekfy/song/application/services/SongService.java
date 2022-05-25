package com.kreitek.kreitekfy.song.application.services;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<SongDTO> getAllSongs();
    Optional<SongDTO> getSongById(Long idSong);
    SongDTO saveSong(SongDTO songDTO);
    void deleteSong(Long idSong);

}
