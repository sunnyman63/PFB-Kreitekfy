package com.kreitek.kreitekfy.userSong.application.dto;

import java.io.Serializable;

public class UserSongSimpleDTO implements Serializable {

    private Long id;
    private Long userId;
    private Long songId;
    private Long personalViews;
    private Long personalValorations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
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
