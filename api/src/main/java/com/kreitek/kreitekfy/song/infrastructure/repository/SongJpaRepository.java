package com.kreitek.kreitekfy.song.infrastructure.repository;

import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SongJpaRepository extends JpaRepository<Song, Long>, JpaSpecificationExecutor<Song> {
    List<Song> findByNameContainsIgnoreCase(String partialName);


//    List<Song> getAllSongsByOrderByInclusion_DateDesc();
}
