package com.kreitek.kreitekfy.user.application.dto;

import com.kreitek.kreitekfy.userSong.application.dto.UserSongDTO;

import java.io.Serializable;
import java.util.List;

public class UserDTO implements Serializable {

    private Long id;
    private boolean admin;
    private String name;

    List<UserSongDTO> songsData;

    public UserDTO() {
    }
    public List<UserSongDTO> getSongsData() {
        return songsData;
    }

    public void setSongsData(List<UserSongDTO> songsData) {
        this.songsData = songsData;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long idUsuario) {
        this.id = idUsuario;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}