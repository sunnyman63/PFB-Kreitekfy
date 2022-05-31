package com.kreitek.kreitekfy.song.infrastructure.repository;

import com.kreitek.kreitekfy.shared.infrastructure.specs.SearchCriteriaHelper;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.song.domain.repository.SongRepository;
import com.kreitek.kreitekfy.song.infrastructure.specs.SongSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return songJpaRepository.findAll();
    }

    @Override
    public List<Song> getSongsByName(String partialName) {
        return songJpaRepository.findByNameContainsIgnoreCase(partialName);
    }

    @Override
    public Page<Song> findAll(Pageable pageable, String filters) {
        SongSpecification specification = new SongSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.songJpaRepository.findAll(specification, pageable);
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

  //@Override
 // public List<Song> getAllSongsByOrderByInclusion_DateDesc() {
        //return songJpaRepository.getAllSongsByOrderByInclusion_DateDesc();
  //}
    }


