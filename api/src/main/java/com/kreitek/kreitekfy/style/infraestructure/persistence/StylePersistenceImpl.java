package com.kreitek.kreitekfy.style.infraestructure.persistence;

import com.kreitek.kreitekfy.shared.infrastructure.specs.SearchCriteriaHelper;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import com.kreitek.kreitekfy.style.domain.persistence.StylePersistence;
import com.kreitek.kreitekfy.style.infraestructure.specs.StyleSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StylePersistenceImpl implements StylePersistence {
    private final StyleRepository styleRepository;

    @Autowired
    public StylePersistenceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public Page<Style> getStylesByCriteriaPaged(Pageable pageable, String filters) {
        StyleSpecification specification = new StyleSpecification(SearchCriteriaHelper.fromFilterString(filters));
        return this.styleRepository.findAll(specification,pageable);
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
    public List<Style> getStyleByName(String partialName) {
        return this.styleRepository.findByNameContainsIgnoreCase(partialName);
    }
}
