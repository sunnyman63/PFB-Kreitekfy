package com.kreitek.kreitekfy.album.application.service.impl;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.application.dto.AlbumSimpleDTO;
import com.kreitek.kreitekfy.album.application.mapper.AlbumMapper;
import com.kreitek.kreitekfy.album.application.service.AlbumService;
import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.album.domain.persistence.AlbumPersistence;
import com.kreitek.kreitekfy.song.application.services.SongService;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumPersistence persistence;
    private final AlbumMapper mapper;
    private final SongService songService;

    @Autowired
    public AlbumServiceImpl(AlbumPersistence persistence, AlbumMapper mapper, SongService songService) {
        this.persistence = persistence;
        this.mapper = mapper;
        this.songService = songService;
    }

    @Override
    public List<Song> addCalculatedValuesToSong(List<Song> iterable) {
        return this.songService.addCalculatedValuesToSong(iterable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlbumDTO> getAlbumsByCriteriaPaged(Pageable pageable, String filter) {
        Page<Album> itemPage = this.persistence.findAll(pageable,filter);
        itemPage.forEach(album -> {
            List<Song> songs = album.getSongs().stream().toList();
            songs = this.addCalculatedValuesToSong(songs);
            album.setSongs(new HashSet<>(songs));
        });
        return itemPage.map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AlbumDTO> getAlbumById(Long albumId) {
        Optional<AlbumDTO> opAlbumDTO = this.persistence
                .getAlbumById(albumId)
                .map(this.mapper::toDto);
        if (opAlbumDTO.isPresent()) {
            Album album = this.mapper.toEntity(opAlbumDTO.get());
            if(album.getSongs() != null){
                List<Song> songs = album.getSongs().stream().toList();
                songs = this.addCalculatedValuesToSong(songs);
                album.setSongs(new HashSet<>(songs));
                opAlbumDTO = Optional.of(this.mapper.toDto(album));
            }
        }
        return opAlbumDTO;
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
        albums.forEach(album -> {
            List<Song> songs = album.getSongs().stream().toList();
            songs = this.addCalculatedValuesToSong(songs);
            album.setSongs(new HashSet<>(songs));
        });
        return this.mapper.toSimpleDto(albums);
    }

}
