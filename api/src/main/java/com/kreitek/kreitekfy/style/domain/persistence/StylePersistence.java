package com.kreitek.kreitekfy.style.domain.persistence;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StylePersistence {
    Page<Style> getStylesByCriteriaPaged(Pageable pageable, String filter);
    List<Style> getAllStyles();
    List<Style> getStyleByName(String partialName);
    Optional<Style> getStyleById(Long styleId);
    Style saveStyle(Style style);
    void deleteStyle(Long styleId);

}
