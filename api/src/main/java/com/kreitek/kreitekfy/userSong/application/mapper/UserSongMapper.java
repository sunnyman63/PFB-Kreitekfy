package com.kreitek.kreitekfy.userSong.application.mapper;

import com.kreitek.kreitekfy.shared.mapper.EntityMapper;
import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.mapper.SongMapper;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.user.application.dto.UserDTO;
import com.kreitek.kreitekfy.user.application.mapper.UserMapper;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;
import com.kreitek.kreitekfy.userSong.application.dto.UserSongSimpleDTO;
import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, SongMapper.class})
public interface UserSongMapper extends EntityMapper<UserSongDTO, UserSong> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "songId", target = "song")
    UserSong toEntity(UserSongDTO userSongDTO);

    @Override
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "song.id", target = "songId")
    @Mapping(source = "song.name", target = "songName")
    @Mapping(source = "user.name", target = "userName")
    UserSongDTO toDto(UserSong userSong);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "song.id", target = "songId")
    UserSongSimpleDTO toSimpleDto(UserSong userSong);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "songId", target = "song")
    UserSong toEntity(UserSongSimpleDTO userSongSimpleDTO);

    default UserSong fromId(Long id) {
        if (id == null) return null;

        UserSong userSong = new UserSong();
        userSong.setId(id);
        return userSong;
    }



}
