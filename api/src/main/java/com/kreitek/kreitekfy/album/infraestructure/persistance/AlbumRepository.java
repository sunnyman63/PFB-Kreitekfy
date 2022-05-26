package com.kreitek.kreitekfy.album.infraestructure.persistance;

import com.kreitek.kreitekfy.album.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long>, JpaSpecificationExecutor<Album> {
    List<Album> findByNameContainsIgnoreCase(String partialName);
}
