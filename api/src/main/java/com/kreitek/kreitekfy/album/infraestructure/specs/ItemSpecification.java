package com.kreitek.kreitekfy.album.infraestructure.specs;


import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.album.infraestructure.specs.shared.EntitySpecification;
import com.kreitek.kreitekfy.album.infraestructure.specs.shared.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class ItemSpecification extends EntitySpecification<Album> implements Specification<Album> {


    public ItemSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }



}