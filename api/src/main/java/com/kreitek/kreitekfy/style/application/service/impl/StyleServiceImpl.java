package com.kreitek.kreitekfy.style.application.service.impl;

import com.kreitek.kreitekfy.style.application.dto.StyleDTO;
import com.kreitek.kreitekfy.style.application.mapper.StyleMapper;
import com.kreitek.kreitekfy.style.application.service.StyleService;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import com.kreitek.kreitekfy.style.domain.persistence.StylePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StyleServiceImpl implements StyleService {

    private final StylePersistence stylePersistence;
    private final StyleMapper styleMapper;

    @Autowired
    public StyleServiceImpl(StylePersistence stylePersistence, StyleMapper styleMapper) {
        this.stylePersistence = stylePersistence;
        this.styleMapper = styleMapper;
    }

    @Override
    public List<StyleDTO> getAllStyles() {
        List<Style> styles = this.stylePersistence.getAllStyles();
        return styleMapper.toDto(styles);
    }

    @Override
    public Optional<StyleDTO> getStyleById(Long styleId) {
        return this.stylePersistence
                .getStyleById(styleId)
                .map(styleMapper::toDto);
    }

    @Override
    public StyleDTO saveStyle(StyleDTO styleDTO) {
        Style style = this.stylePersistence.saveStyle(this.styleMapper.toEntity(styleDTO));

        return this.styleMapper.toDto(style);
    }

    @Override
    public void deleteStyle(Long styleId) {
        this.stylePersistence.deleteStyle(styleId);
    }

    @Override
    public List<StyleDTO> getStyleByName(String partialName) {
        List<Style> styles = this.stylePersistence.getStyleByName(partialName);
        return styleMapper.toDto(styles);
    }
}
