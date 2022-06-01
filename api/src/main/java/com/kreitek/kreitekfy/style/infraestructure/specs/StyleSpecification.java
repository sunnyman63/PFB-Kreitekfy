package com.kreitek.kreitekfy.style.infraestructure.specs;

import com.kreitek.kreitekfy.shared.infrastructure.specs.EntitySpecification;
import com.kreitek.kreitekfy.shared.infrastructure.specs.SearchCriteria;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class StyleSpecification extends EntitySpecification<Style> implements Specification<Style> {

    public StyleSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

}
