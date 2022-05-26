package com.kreitek.kreitekfy.song.infrastructure.specs;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.shared.infrastructure.specs.EntitySpecification;
import com.kreitek.kreitekfy.shared.infrastructure.specs.SearchCriteria;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SongSpecification extends EntitySpecification<Song> implements Specification<Song>{
    public SongSpecification(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }
}
