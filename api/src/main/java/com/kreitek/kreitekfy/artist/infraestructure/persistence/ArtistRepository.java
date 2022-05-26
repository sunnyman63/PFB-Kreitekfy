package com.kreitek.kreitekfy.artist.infraestructure.persistence;

import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long>, JpaSpecificationExecutor<Artist> {
    List<Artist> findByNameContainsIgnoreCase(String partialName);
}
