package com.kreitek.kreitekfy.user.infraestructure.persistence;

import com.kreitek.kreitekfy.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
