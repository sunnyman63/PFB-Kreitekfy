package com.kreitek.kreitekfy.user.application.mapper;

import com.kreitek.kreitekfy.shared.mapper.EntityMapper;
import com.kreitek.kreitekfy.user.application.dto.UserDTO;
import com.kreitek.kreitekfy.user.domain.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper <UserDTO, User>{

}
