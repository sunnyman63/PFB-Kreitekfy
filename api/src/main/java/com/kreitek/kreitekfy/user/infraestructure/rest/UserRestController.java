package com.kreitek.kreitekfy.user.infraestructure.rest;

import com.kreitek.kreitekfy.user.application.dto.UserDTO;
import com.kreitek.kreitekfy.user.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = "application/json")
    ResponseEntity<List<UserDTO>> getAllUsers(){
    List<UserDTO> users = this.userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/users", produces = "application/json", consumes = "application/json")
    ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO userDTO){
        userDTO = this.userService.saveUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

}
