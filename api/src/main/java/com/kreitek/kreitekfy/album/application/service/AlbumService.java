package com.kreitek.kreitekfy.album.application.service;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.application.dto.AlbumSimpleDTO;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.kreitek.kreitekfy.album.domain.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<AlbumSimpleDTO> getAlbums();
    List<AlbumSimpleDTO> getAlbumByName(String partialName);
    Page<AlbumDTO> getAlbumsByCriteriaPaged(Pageable pageable, String filter);
    Optional<AlbumDTO> getAlbumById(Long albumId);
    AlbumDTO saveAlbum(AlbumDTO albumDTO);
    void deleteAlbum(Long albumId);
    List<Song> addCalculatedValuesToSong(List<Song> iterable);

}
