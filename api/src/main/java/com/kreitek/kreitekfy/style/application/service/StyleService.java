package com.kreitek.kreitekfy.style.application.service;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.style.application.dto.StyleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StyleService {
    Page<StyleDTO> getStylesByCriteriaPaged(Pageable pageable, String filter);
    List<StyleDTO> getAllStyles();
    Optional<StyleDTO> getStyleById(Long styleId);
    StyleDTO saveStyle(StyleDTO style);
    void deleteStyle(Long styleId);
    List<StyleDTO> getStyleByName(String partialName);
}
