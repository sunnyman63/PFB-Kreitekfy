package com.kreitek.kreitekfy.style.application.service;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.style.application.dto.StyleDTO;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StyleService {
    List<StyleDTO> getAllStyles();
    Optional<StyleDTO> getStyleById(Long styleId);
    StyleDTO saveStyle(StyleDTO style);
    void deleteStyle(Long styleId);
    List<StyleDTO> getStyleByName(String partialName);
}
