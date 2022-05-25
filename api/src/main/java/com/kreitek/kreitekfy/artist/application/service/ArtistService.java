package com.kreitek.kreitekfy.artist.application.service;

import com.kreitek.kreitekfy.artist.application.dto.ArtistDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArtistService {
    List<ArtistDTO> getAllArtists();
    Optional<ArtistDTO> getArtistById(Long artistId);
    ArtistDTO saveArtist(ArtistDTO artist);
    void deleteArtist(Long artistId);
    List<ArtistDTO> getArtistByName(String partialName);
}
