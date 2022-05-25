package com.kreitek.kreitekfy.user.application.service;


import com.kreitek.kreitekfy.user.application.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();
    Optional<UserDTO> getUsersById(Long id);
    UserDTO saveUser(UserDTO user);
    void deleteUser(Long idUser);
    List<UserDTO> getUsersByName(String partialName);

}
