package com.kreitek.kreitekfy.album.application.mapper;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.shared.mapper.EntityMapper;
import com.kreitek.kreitekfy.song.application.mapper.SongMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { SongMapper.class })
public interface AlbumMapper extends EntityMapper<AlbumDTO, Album> {

    @Mapping(target = "songs", ignore = true)
    Album toEntity(AlbumDTO albumDTO);

    default Album fromId(Long id){
        if (id == null) return null;
        Album album = new Album();
        album.setId(id);
        return album;
    }
}
