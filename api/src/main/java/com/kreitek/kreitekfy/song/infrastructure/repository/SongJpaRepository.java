package com.kreitek.kreitekfy.song.infrastructure.repository;

import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongJpaRepository extends JpaRepository<Song, Long> {

    List<Song> getAllSongsByOrderInclusion_DateDesc();
}
