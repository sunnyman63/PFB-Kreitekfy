package com.kreitek.kreitekfy.album.application.dto;

import com.kreitek.kreitekfy.song.application.dto.SongSimpleDTO;

import java.io.Serializable;
import java.util.List;

public class AlbumDTO implements Serializable {

    private Long id;
    private String name;
    private byte[] image;
    private List<SongSimpleDTO> songs;

    private Long artistId;
    private String artistName;

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

    public List<SongSimpleDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongSimpleDTO> songs) {
        this.songs = songs;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
