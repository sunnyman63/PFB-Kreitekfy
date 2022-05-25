package com.kreitek.kreitekfy.album.infraestructure.persistance;

import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.shared.infrastructure.repository.EntityRepository;

import java.util.List;

public interface AlbumRepository extends EntityRepository<Album> {
    List<Album> findByNameContainsIgnoreCase(String partialName);
}
