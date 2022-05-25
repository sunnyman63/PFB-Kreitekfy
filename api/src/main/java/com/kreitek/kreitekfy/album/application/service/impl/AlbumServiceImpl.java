package com.kreitek.kreitekfy.album.application.service.impl;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.application.mapper.AlbumMapper;
import com.kreitek.kreitekfy.album.application.service.AlbumService;
import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.album.domain.persistence.AlbumPersistence;

import java.util.List;
import java.util.Optional;

public class AlbumServiceImpl implements AlbumService {

    private final AlbumPersistence persistence;
    private final AlbumMapper mapper;

    public AlbumServiceImpl(AlbumPersistence persistence, AlbumMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = this.persistence.getAllAlbums();
        return this.mapper.toDto(albums);
    }

    @Override
    public Optional<AlbumDTO> getAlbumById(Long albumId) {
        return this.persistence
                .getAlbumById(albumId)
                .map(this.mapper::toDto);
    }

    @Override
    public AlbumDTO saveAlbum(AlbumDTO albumDTO) {
        Album album = this.persistence.saveAlbum(this.mapper.toEntity(albumDTO));
        return this.mapper.toDto(album);
    }

    @Override
    public void deleteAlbum(Long albumId) {
        this.persistence.deleteAlbum(albumId);
    }

    @Override
    public List<AlbumDTO> getAlbumByName(String partialName) {
        List<Album> albums = this.persistence.getAlbumByName(partialName);
        return this.mapper.toDto(albums);
    }
}
