package com.kreitek.kreitekfy.song.application.mapper;

import com.kreitek.kreitekfy.album.application.mapper.AlbumMapper;
import com.kreitek.kreitekfy.shared.mapper.EntityMapper;
import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.style.application.mapper.StyleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AlbumMapper.class, StyleMapper.class })
public interface SongMapper extends EntityMapper<SongDTO, Song> {

    @Override
    @Mapping(source = "albumId", target = "album")
    @Mapping(source = "styleId", target = "style")
    Song toEntity(SongDTO songDTO);

    @Override
    @Mapping(source = "album.id", target = "albumId")
    @Mapping(source = "album.name", target = "albumName")
    @Mapping(source = "album.image", target = "albumImage")
    @Mapping(source = "style.id", target = "styleId")
    @Mapping(source = "style.name", target = "styleName")
    SongDTO toDto(Song song);

    @Mapping(source = "style.id", target = "styleId")
    @Mapping(source = "style.name", target = "styleName")
    SongSimpleDTO toSimpleDto(Song song);

    @Mapping(source = "styleId", target = "style")
    @Mapping(target = "album", ignore = true)
    Song toEntity(SongSimpleDTO songSimpleDTO);

}
