package com.kreitek.kreitekfy.song.domain.repository;

import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongRepository {
    Page<Song> findAll(Pageable pageable, String filters);
    Song save(Song entity);
    void deleteById(Long id);
    Optional<Song> findById(Long id);
//    List<Song> getAllSongsByOrderByInclusion_DateDesc();
}
