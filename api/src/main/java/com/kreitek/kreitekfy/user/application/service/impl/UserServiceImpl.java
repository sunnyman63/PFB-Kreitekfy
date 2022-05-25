package com.kreitek.kreitekfy.user.application.service.impl;

import com.kreitek.kreitekfy.user.application.dto.UserDTO;
import com.kreitek.kreitekfy.user.application.mapper.UserMapper;
import com.kreitek.kreitekfy.user.application.service.UserService;
import com.kreitek.kreitekfy.user.domain.entity.User;
import com.kreitek.kreitekfy.user.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserPersistence persistence;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserPersistence persistence, UserMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.persistence.getAllUsers();
        return this.mapper.toDto(users);
    }

    @Override
    public Optional<UserDTO> getUsersById(Long userId) {

        return this.persistence.getUsersById(userId).map(mapper::toDto);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
       User user = this.persistence.saveUser(this.mapper.toEntity(userDTO));
        return this.mapper.toDto(user);
    }

    @Override
    public void deleteUser(Long idUser) {
        this.persistence.deleteUser(idUser);
    }

    @Override
    public List<UserDTO> getUsersByName(String partialName) {

        return null;
    }
}
