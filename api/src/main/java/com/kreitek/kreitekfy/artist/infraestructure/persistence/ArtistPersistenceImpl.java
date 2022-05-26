package com.kreitek.kreitekfy.artist.infraestructure.persistence;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.artist.domain.persistence.ArtistPersistence;
import com.kreitek.kreitekfy.artist.infraestructure.specs.ArtistSpecification;
import com.kreitek.kreitekfy.shared.infrastructure.specs.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistPersistenceImpl implements ArtistPersistence {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistPersistenceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }


    @Override
    public Page<Artist> findAll(Pageable pageable, String filters) {

        ArtistSpecification specification = new ArtistSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.artistRepository.findAll(specification,pageable);
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
        return this.artistRepository.findByNameContainsIgnoreCase(partialName);
    }
}
