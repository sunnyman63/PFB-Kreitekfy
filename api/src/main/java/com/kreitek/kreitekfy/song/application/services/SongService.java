package com.kreitek.kreitekfy.song.application.services;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<SongSimpleDTO> getSongs();
    List<SongSimpleDTO> getSongsByName(String partialName);
    List<SongDTO> getNewestSongs();
    List<SongSimpleDTO> findByOrderByTotalRateDesc();
    Page<SongDTO> getSongByCriteriaPaged(Pageable pageable, String filter);
    Optional<SongDTO> getSongById(Long idSong);
    SongDTO saveSong(SongDTO songDTO);
    void deleteSong(Long idSong);

}
