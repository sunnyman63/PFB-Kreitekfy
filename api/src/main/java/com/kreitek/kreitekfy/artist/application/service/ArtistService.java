package com.kreitek.kreitekfy.artist.application.service;

import com.kreitek.kreitekfy.artist.application.dto.ArtistDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArtistService {
    Page<ArtistDTO> getArtistByCriteriaPaged(Pageable pageable, String filter);
    Optional<ArtistDTO> getArtistById(Long artistId);
    ArtistDTO saveArtist(ArtistDTO artist);
    void deleteArtist(Long artistId);
    List<ArtistDTO> getArtistByName(String partialName);
}
