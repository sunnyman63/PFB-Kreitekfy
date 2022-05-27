package com.kreitek.kreitekfy.userSong.infraestructure.rest;


import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongSimpleDTO;
import com.kreitek.kreitekfy.userSong.application.service.UserSongService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/usersong")
public class UserSongController {

    private final UserSongService userSongService;

    public UserSongController(UserSongService userSongService) {
        this.userSongService = userSongService;
    }

    @PostMapping(consumes = "application/json")
    ResponseEntity<UserSongSimpleDTO> insertUserSong(@RequestBody UserSongSimpleDTO userSongSimpleDTO){
        userSongSimpleDTO = this.userSongService.saveUserSong(userSongSimpleDTO);
        return new ResponseEntity<>(userSongSimpleDTO, HttpStatus.CREATED);

    }
    @PutMapping(consumes = "application/json")
    ResponseEntity<UserSongSimpleDTO> updateUserSongView(@RequestBody UserSongSimpleDTO userSongSimpleDTO){
        userSongSimpleDTO = this.userSongService.updateUserSong(userSongSimpleDTO);
        if (userSongSimpleDTO == null)
        {return new ResponseEntity<>(userSongSimpleDTO, HttpStatus.UNAUTHORIZED);
        }else{
        return new ResponseEntity<>(userSongSimpleDTO, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/rate", consumes = "application/json")
    ResponseEntity<UserSongSimpleDTO> updateUserSongRating(@RequestBody UserSongSimpleDTO userSongSimpleDTO){

        userSongSimpleDTO = this.userSongService.updateUserSongRating(userSongSimpleDTO);
        if (userSongSimpleDTO == null)
        {return new ResponseEntity<>(userSongSimpleDTO, HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity<>(userSongSimpleDTO, HttpStatus.OK);
        }    }


}
