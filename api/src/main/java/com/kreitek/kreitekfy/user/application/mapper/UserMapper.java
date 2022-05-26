package com.kreitek.kreitekfy.user.application.mapper;

import com.kreitek.kreitekfy.shared.mapper.EntityMapper;
import com.kreitek.kreitekfy.user.application.dto.UserDTO;
import com.kreitek.kreitekfy.user.domain.entity.User;
import com.kreitek.kreitekfy.userSong.application.mapper.UserSongMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {UserSongMapper.class})
public interface UserMapper extends EntityMapper <UserDTO, User>{

    default User fromId(Long id) {
        if (id == null) return null;

        User user = new User();
        user.setId(id);
        return user;
    }


}
