package com.kreitek.kreitekfy.style.infraestructure.persistence;

import com.kreitek.kreitekfy.style.domain.entity.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
}