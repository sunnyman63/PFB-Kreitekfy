package com.kreitek.kreitekfy.style.infraestructure.persistence;

import com.kreitek.kreitekfy.shared.infrastructure.repository.EntityRepository;
import com.kreitek.kreitekfy.style.domain.entity.Style;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends EntityRepository<Style> {
}
