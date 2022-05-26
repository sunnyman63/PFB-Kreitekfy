package com.kreitek.kreitekfy.album.application.service;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.kreitek.kreitekfy.album.domain.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<AlbumDTO> getAllAlbums();
    List<AlbumDTO> getAlbumsByArtist(Long artistId);
    Optional<Album> getAlbumByArtist(Long artistId, Long albumId);
    Optional<AlbumDTO> getAlbumById(Long albumId);
    AlbumDTO saveAlbum(AlbumDTO albumDTO);
    void deleteAlbum(Long albumId);
    List<AlbumDTO> getAlbumByName(String partialName);
    Page<AlbumDTO> getAlbumsByCriteriaPaged(Pageable pageable, String filter);
}
