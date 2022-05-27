package com.kreitek.kreitekfy.userSong.application.service;

import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongSimpleDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserSongService {
    
    UserSongSimpleDTO saveUserSong(UserSongSimpleDTO userSongSimpleDTO);
    UserSongSimpleDTO updateUserSong(UserSongSimpleDTO userSongSimpleDTO);
    UserSongSimpleDTO updateUserSongRating(UserSongSimpleDTO userSongSimpleDTO);
    Optional<UserSongDTO> getUserSongById(Long id);

}
