package com.kreitek.kreitekfy.style.infraestructure.persistence;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import com.kreitek.kreitekfy.style.domain.persistence.StylePersistence;

import java.util.List;
import java.util.Optional;

public class StylePersistenceImpl implements StylePersistence {
    private final StyleRepository styleRepository;

    public StylePersistenceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public List<Style> getAllStyles() {
        return this.styleRepository.findAll();
    }

    @Override
    public Optional<Style> getStyleById(Long styleId) {
        return this.styleRepository.findById(styleId);
    }

    @Override
    public Style saveStyle(Style style) {
        return this.styleRepository.save(style);
    }

    @Override
    public void deleteStyle(Long styleId) {
        this.styleRepository.deleteById(styleId);
    }

    @Override
    public List<Artist> getStyleByName(String partialName) {
        return null;
    }
}
