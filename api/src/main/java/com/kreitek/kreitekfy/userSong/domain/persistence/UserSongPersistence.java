package com.kreitek.kreitekfy.userSong.domain.persistence;

import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;

import java.util.Optional;

public interface UserSongPersistence {
    UserSong saveUserSong(UserSong userSong);
    UserSong updateUserSong(UserSong userSong);
    UserSong updateUserSongRating(UserSong userSong);
    Optional<UserSong> getUserSongById(Long id);
}
