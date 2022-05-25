package com.kreitek.kreitekfy.user.infraestructure.persistence;

import com.kreitek.kreitekfy.shared.infrastructure.repository.EntityRepository;
import com.kreitek.kreitekfy.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends EntityRepository<User> {
}
