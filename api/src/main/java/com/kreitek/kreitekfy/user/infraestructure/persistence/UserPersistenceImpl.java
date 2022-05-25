package com.kreitek.kreitekfy.user.infraestructure.persistence;

import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.user.application.mapper.UserMapper;
import com.kreitek.kreitekfy.user.domain.entity.User;
import com.kreitek.kreitekfy.user.domain.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserPersistenceImpl implements UserPersistence {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserPersistenceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getUsersById(Long idUser) {
        return this.userRepository.findById(idUser);
    }

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long idUser) {
         this.userRepository.deleteById(idUser);
    }

    @Override
    public List<Album> getUsersByName(String partialName) {
        return null;
    }
}
