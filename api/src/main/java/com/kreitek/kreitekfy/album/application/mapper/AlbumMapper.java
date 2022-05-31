package com.kreitek.kreitekfy.album.application.mapper;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;
import com.kreitek.kreitekfy.album.application.dto.AlbumSimpleDTO;
import com.kreitek.kreitekfy.album.domain.entity.Album;
import com.kreitek.kreitekfy.artist.application.mapper.ArtistMapper;
import com.kreitek.kreitekfy.shared.mapper.EntityMapper;
import com.kreitek.kreitekfy.song.application.mapper.SongMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ArtistMapper.class, SongMapper.class  })
public interface AlbumMapper extends EntityMapper<AlbumDTO, Album> {

    @Override
    @Mapping(target = "songs", ignore = true)
    Album toEntity(AlbumDTO dto);

    List<AlbumSimpleDTO> toSimpleDto(List<Album> albums);

    @Mapping(target = "songs", ignore = true)
    Album toDto(AlbumSimpleDTO albumSimpleDTO);

    default Album fromId(Long id){
        if (id == null) return null;
        Album album = new Album();
        album.setId(id);
        return album;}

}
