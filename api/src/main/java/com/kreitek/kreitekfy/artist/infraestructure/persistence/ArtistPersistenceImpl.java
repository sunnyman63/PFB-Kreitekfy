package com.kreitek.kreitekfy.artist.infraestructure.persistence;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.artist.domain.persistence.ArtistPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistPersistenceImpl implements ArtistPersistence {

    private final ArtistRepository artistRepository;

    public ArtistPersistenceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllArtists() {
        return this.artistRepository.findAll();
    }

    @Override
    public Optional<Artist> getArtistById(Long artistId) {
        return this.artistRepository.findById(artistId);
    }

    @Override
    public Artist saveArtist(Artist artist) {
        return this.artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Long artistId) {
        this.artistRepository.deleteById(artistId);
    }

    @Override
    public List<Artist> getArtistByName(String partialName) {
        return null;
    }
}
