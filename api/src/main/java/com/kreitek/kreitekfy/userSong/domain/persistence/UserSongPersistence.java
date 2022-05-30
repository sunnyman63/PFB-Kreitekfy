package com.kreitek.kreitekfy.userSong.domain.persistence;

import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongSimpleDTO;
import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;

import java.util.List;
import java.util.Optional;

public interface UserSongPersistence {
    UserSong saveUserSong(UserSong userSong);
    UserSong updateUserSong(UserSong userSong);
    UserSong updateUserSongRating(UserSong userSong);
    Optional<UserSong> getUserSongById(Long id);

    List<UserSong> getUserSongBySong_Id(Long id);
    Boolean existUserSongBySongIdAndUserId(Long id, Long id1);
    UserSong findUserSongBySongIdAndUserId(Long id, Long id1);
}
