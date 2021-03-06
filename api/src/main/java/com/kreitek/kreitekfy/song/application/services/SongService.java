package com.kreitek.kreitekfy.song.application.services;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongService {

    List<SongSimpleDTO> getSongs();
    List<SongSimpleDTO> getSongsByName(String partialName);
    List<SongDTO> getAllSongsByOrderByInclusionDateDesc(Long styleId);
    List<SongDTO> findByOrderByTotalRateDesc(Long styleId);
    List<SongDTO> findByOrderByTotalViewsDesc(Long styleId);
    Page<SongDTO> getSongByCriteriaPaged(Pageable pageable, String filter);
    Optional<SongDTO> getSongById(Long idSong);

    List<Song> addCalculatedValuesToSong(List<Song> iterable);

    SongDTO saveSong(SongDTO songDTO);
    void deleteSong(Long idSong);

    List<SongDTO> findByUserPreferences(Long idUser, Long styleId);
}
