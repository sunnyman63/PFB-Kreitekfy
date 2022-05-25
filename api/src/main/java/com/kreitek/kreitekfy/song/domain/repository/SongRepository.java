package com.kreitek.kreitekfy.song.domain.repository;

import com.kreitek.kreitekfy.song.domain.entity.Song;

import java.util.List;
import java.util.Optional;

public interface SongRepository {
    List<Song> findAll();
    Song save(Song entity);
    void deleteById(Long id);
    Optional<Song> findById(Long id);
//    List<Song> getAllSongsByOrderByInclusion_DateDesc();
}
