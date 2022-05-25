package com.kreitek.kreitekfy.album.application.mapper;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.shared.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlbumMapper extends EntityMapper<AlbumDTO, Album> {
}
