package com.kreitek.kreitekfy.song.application.mapper;

import com.kreitek.kreitekfy.album.application.mapper.AlbumMapper;
import com.kreitek.kreitekfy.shared.mapper.EntityMapper;
import com.kreitek.kreitekfy.song.application.dto.SongDTO;
import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;
import com.kreitek.kreitekfy.song.domain.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AlbumMapper.class })
public interface SongMapper extends EntityMapper<SongDTO, Song> {

    @Override
    @Mapping(source = "albumId", target = "album")
    Song toEntity(SongDTO songDTO);

    @Override
    @Mapping(source = "album.id", target = "albumId")
    @Mapping(source = "album.name", target = "albumName")
    @Mapping(source = "album.image", target = "albumImage")
    SongDTO toDto(Song song);

    SongSimpleDTO toSimpleDto(Song song);
    @Mapping(target = "album", ignore = true)
    Song toEntity(SongSimpleDTO songSimpleDTO);
}
