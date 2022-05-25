package com.kreitek.kreitekfy.artist.infraestructure.persistence;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
}
