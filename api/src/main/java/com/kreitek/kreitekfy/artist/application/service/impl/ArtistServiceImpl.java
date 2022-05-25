package com.kreitek.kreitekfy.artist.application.service.impl;

import com.kreitek.kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.kreitekfy.artist.application.mapper.ArtistMapper;
import com.kreitek.kreitekfy.artist.application.service.ArtistService;
import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.artist.domain.persistence.ArtistPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistPersistence artistPersistence;
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistServiceImpl(ArtistPersistence artistPersistence, ArtistMapper artistMapper) {
        this.artistPersistence = artistPersistence;
        this.artistMapper = artistMapper;
    }

    @Override
    public List<ArtistDTO> getAllArtists() {
        List<Artist> artists = this.artistPersistence.getAllArtists();
        return artistMapper.toDto(artists);
    }

    @Override
    public Optional<ArtistDTO> getArtistById(Long artistId) {
        return this.artistPersistence
                .getArtistById(artistId)
                .map(artistMapper::toDto);
    }

    @Override
    public ArtistDTO saveArtist(ArtistDTO artistDTO) {
        Artist artist = this.artistPersistence.saveArtist(this.artistMapper.toEntity(artistDTO));
        return this.artistMapper.toDto(artist);
    }

    @Override
    public void deleteArtist(Long artistId) {
        this.artistPersistence.deleteArtist(artistId);

    }

    @Override
    public List<ArtistDTO> getArtistByName(String partialName) {
        return null;
    }
}