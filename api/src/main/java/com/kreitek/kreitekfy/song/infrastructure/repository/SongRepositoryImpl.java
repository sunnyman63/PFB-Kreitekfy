package com.kreitek.kreitekfy.song.infrastructure.repository;

import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.song.domain.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SongRepositoryImpl implements SongRepository {

    private final SongJpaRepository songJpaRepository;

    @Autowired
    public SongRepositoryImpl(SongJpaRepository songJpaRepository) {
        this.songJpaRepository = songJpaRepository;
    }


    @Override
    public List<Song> findAll() {
        return this.songJpaRepository.findAll();
    }

    @Override
    public Song save(Song entity) {
        return this.songJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.songJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songJpaRepository.findById(id);
    }

    @Override
    public List<Song> getAllSongsByOrderInclusion_DateDesc() {
        return songJpaRepository.getAllSongsByOrderInclusion_DateDesc();
    }


}
