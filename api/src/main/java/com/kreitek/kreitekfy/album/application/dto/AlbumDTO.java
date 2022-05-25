package com.kreitek.kreitekfy.album.application.dto;

import java.io.Serializable;

public class AlbumDTO implements Serializable {

    private Long id;
    private String name;
    private byte[] image;

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
}
