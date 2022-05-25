package com.kreitek.kreitekfy.style.domain.persistence;

import com.kreitek.kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.style.application.dto.StyleDTO;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StylePersistence {
    List<Style> getAllStyles();
    Optional<Style> getStyleById(Long styleId);
    Style saveStyle(Style style);
    void deleteStyle(Long styleId);
    List<Artist> getStyleByName(String partialName);
}
