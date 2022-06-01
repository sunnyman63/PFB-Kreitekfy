package com.kreitek.kreitekfy.userSong.application.service;

import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongSimpleDTO;
import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserSongService {
    
    UserSongSimpleDTO saveUserSong(UserSongSimpleDTO userSongSimpleDTO);
    UserSongSimpleDTO updateUserSong(UserSongSimpleDTO userSongSimpleDTO);
    UserSongSimpleDTO updateUserSongRating(UserSongSimpleDTO userSongSimpleDTO);
    Optional<UserSongDTO> getUserSongById(Long id);
    Boolean existUserSongBySongIdAndUserId(Long idSong, Long idUser);
    UserSongSimpleDTO findUserSongBySongIdAndUserId(Long idSong, Long idUser);
    UserSongSimpleDTO findUserSongbyUserId(Long idUser);
    List<UserSongDTO> getAllUserSongBySong_Id(Long id);

}
