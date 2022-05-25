package com.kreitek.kreitekfy.song.infrastructure.repository;

import com.kreitek.kreitekfy.shared.infrastructure.repository.EntityRepository;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.song.domain.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SongRepositoryImpl implements SongRepository {

    private final EntityRepository<Song> entityRepository;

    @Autowired
    public SongRepositoryImpl(EntityRepository<Song> entityRepository) {
        this.entityRepository = entityRepository;
    }


    @Override
    public List<Song> findAll() {
        return this.entityRepository.findAll();
    }

    @Override
    public Song save(Song entity) {
        return this.entityRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.entityRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return entityRepository.findById(id);
    }
}
