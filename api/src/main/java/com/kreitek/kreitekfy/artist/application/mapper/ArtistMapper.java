package com.kreitek.kreitekfy.artist.application.mapper;

import com.kreitek.kreitekfy.album.application.mapper.AlbumMapper;
import com.kreitek.kreitekfy.artist.application.dto.ArtistDTO;
import com.kreitek.kreitekfy.artist.domain.entity.Artist;
import com.kreitek.kreitekfy.shared.mapper.EntityMapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper extends EntityMapper<ArtistDTO, Artist> {
}
