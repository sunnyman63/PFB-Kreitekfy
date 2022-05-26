package com.kreitek.kreitekfy.album.infraestructure.persistance;

import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.album.domain.persistence.AlbumPersistence;
import com.kreitek.kreitekfy.album.infraestructure.specs.AlbumSpecification;
import com.kreitek.kreitekfy.shared.infrastructure.specs.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AlbumPersistenceImpl implements AlbumPersistence {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumPersistenceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Page<Album> findAll(Pageable pageable, String filters) {
        AlbumSpecification specification = new AlbumSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.albumRepository.findAll(specification,pageable);
    }
    @Override
    public Optional<Album> getAlbumById(Long albumId) {
        return this.albumRepository.findById(albumId);
    }

    @Override
    public Album saveAlbum(Album album) {
        return this.albumRepository.save(album);
    }

    @Override
    public void deleteAlbum(Long albumId) {
        this.albumRepository.deleteById(albumId);
    }

    @Override
    public List<Album> getAlbumByName(String partialName) {
        return this.albumRepository.findByNameContainsIgnoreCase(partialName);
    }
}
