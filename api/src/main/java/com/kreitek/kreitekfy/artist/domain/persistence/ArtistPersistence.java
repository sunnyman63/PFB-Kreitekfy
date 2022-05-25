package com.kreitek.kreitekfy.artist.domain.persistence;

import com.kreitek.kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistPersistence {
    List<Artist> getAllArtists();
    Optional<Artist> getArtistById(Long artistId);
    Artist saveArtist(Artist artist);
    void deleteArtist(Long artistId);
    List<Artist> getArtistByName(String partialName);
}
