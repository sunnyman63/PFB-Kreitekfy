package com.kreitek.kreitekfy.artist.application.dto;

import com.kreitek.kreitekfy.album.application.dto.AlbumDTO;

import java.io.Serializable;
import java.util.List;

public class ArtistDTO implements Serializable {

    private Long id;
    private String name;

    public ArtistDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}