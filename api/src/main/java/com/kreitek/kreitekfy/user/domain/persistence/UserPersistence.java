package com.kreitek.kreitekfy.user.domain.persistence;

import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.user.domain.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserPersistence {
    List<User> getAllUsers();
    Optional <User> getUsersById(Long id);
    User saveUser(User user);
    void deleteUser(Long idUser);
    List<Album> getUsersByName(String partialName);

}
