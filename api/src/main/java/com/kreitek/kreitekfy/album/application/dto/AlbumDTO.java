package com.kreitek.kreitekfy.album.application.dto;

import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;

import java.io.Serializable;
import java.util.List;

public class AlbumDTO implements Serializable {

    private Long id;
    private String name;
    private byte[] image;
    private String imageType;
    private List<SongSimpleDTO> songs;

    public AlbumDTO() {
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public List<SongSimpleDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongSimpleDTO> songs) {
        this.songs = songs;
    }


}
