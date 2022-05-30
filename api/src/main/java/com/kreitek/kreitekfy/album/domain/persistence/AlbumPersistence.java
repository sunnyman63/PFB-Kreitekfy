package com.kreitek.kreitekfy.album.domain.persistence;

import com.kreitek.kreitekfy.album.domain.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AlbumPersistence {
    Page<Album> findAll(Pageable pageable, String filter);
    List<Album> findAll();
    List<Album> getAlbumByName(String partialName);
    Optional<Album> getAlbumById(Long albumId);
    Album saveAlbum(Album album);
    void deleteAlbum(Long albumId);

}
