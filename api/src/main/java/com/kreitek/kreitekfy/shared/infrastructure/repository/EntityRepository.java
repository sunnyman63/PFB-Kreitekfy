package com.kreitek.kreitekfy.shared.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository<E> extends JpaRepository<E, Long> {
}
