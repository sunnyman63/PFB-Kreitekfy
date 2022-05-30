package com.kreitek.kreitekfy.album.application.service.impl;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.application.dto.AlbumSimpleDTO;
import com.kreitek.kreitekfy.album.application.mapper.AlbumMapper;
import com.kreitek.kreitekfy.album.application.service.AlbumService;
import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.album.domain.persistence.AlbumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumPersistence persistence;
    private final AlbumMapper mapper;

    @Autowired
    public AlbumServiceImpl(AlbumPersistence persistence, AlbumMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumDTO> getAlbumsByCriteriaPaged(Pageable pageable, String filter) {
        Page<Album> itemPage = this.persistence.findAll(pageable,filter);
        return itemPage.map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlbumDTO> getAlbumsByArtist(Long artistId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Album> getAlbumByArtist(Long artistId, Long albumId) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlbumDTO> getAlbumById(Long albumId) {
        return this.persistence
                .getAlbumById(albumId)
                .map(this.mapper::toDto);
    }

    @Override
    @Transactional
    public AlbumDTO saveAlbum(AlbumDTO albumDTO) {
        Album album = this.persistence.saveAlbum(this.mapper.toEntity(albumDTO));
        return this.mapper.toDto(album);
    }

    @Override
    @Transactional
    public void deleteAlbum(Long albumId) {
        this.persistence.deleteAlbum(albumId);
    }

    @Override
    public List<AlbumSimpleDTO> getAlbums() {
        List <Album> albums = this.persistence.findAll();
        return this.mapper.toSimpleDto(albums);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlbumSimpleDTO> getAlbumByName(String partialName) {
        List<Album> albums = this.persistence.getAlbumByName(partialName);
        return this.mapper.toSimpleDto(albums);
    }

}
