package com.kreitek.kreitekfy.artist.infraestructure.persistence;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.shared.infrastructure.repository.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends EntityRepository<Artist> {
}
