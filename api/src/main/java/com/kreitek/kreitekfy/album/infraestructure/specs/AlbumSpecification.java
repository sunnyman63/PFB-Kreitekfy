package com.kreitek.kreitekfy.album.infraestructure.specs;


import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.shared.infrastructure.specs.EntitySpecification;
import com.kreitek.kreitekfy.shared.infrastructure.specs.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class AlbumSpecification extends EntitySpecification<Album> implements Specification<Album> {

    public AlbumSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

}