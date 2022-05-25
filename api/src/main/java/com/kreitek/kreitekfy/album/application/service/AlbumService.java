package com.kreitek.kreitekfy.album.application.service;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<AlbumDTO> getAllAlbums();
    Optional<AlbumDTO> getAlbumById(Long albumId);
    AlbumDTO saveAlbum(AlbumDTO albumDTO);
    void deleteAlbum(Long albumId);
    List<AlbumDTO> getAlbumByName(String partialName);
}
