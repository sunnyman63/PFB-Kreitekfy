package com.kreitek.kreitekfy.userSong.application.dto;

import java.io.Serializable;

public class UserSongDTO implements Serializable {
    private String userName;

    private Long songId;

    private Long userId;

    private String songName;

    private Long personalViews;

    private Long personalValorations;

    @Override
    public String toString() {
        return "UserSongDTO{" +
                "userName='" + userName + '\'' +
                ", songId=" + songId +
                ", userId=" + userId +
                ", songName='" + songName + '\'' +
                ", personalViews=" + personalViews +
                ", personalValorations=" + personalValorations +
                '}';
    }

    public UserSongDTO() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Long getPersonalViews() {
        return personalViews;
    }

    public void setPersonalViews(Long personalViews) {
        this.personalViews = personalViews;
    }

    public Long getPersonalValorations() {
        return personalValorations;
    }

    public void setPersonalValorations(Long personalValorations) {
        this.personalValorations = personalValorations;
    }



}
