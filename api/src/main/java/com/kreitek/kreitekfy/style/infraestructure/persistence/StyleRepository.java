package com.kreitek.kreitekfy.style.infraestructure.persistence;

import com.kreitek.kreitekfy.style.domain.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long>, JpaSpecificationExecutor<Style> {
    List<Style> findByNameContainsIgnoreCase(String partialName);

}
