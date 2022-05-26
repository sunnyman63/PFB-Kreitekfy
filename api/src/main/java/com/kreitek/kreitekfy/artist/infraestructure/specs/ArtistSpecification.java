package com.kreitek.kreitekfy.artist.infraestructure.specs;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.shared.infrastructure.specs.EntitySpecification;
import com.kreitek.kreitekfy.shared.infrastructure.specs.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ArtistSpecification extends EntitySpecification<Artist> implements Specification<Artist> {

    public ArtistSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

}
