package com.kreitek.kreitekfy.userSong.domain.entity;

import com.kreitek.kreitekfy.song.domain.entity.Song;
import com.kreitek.kreitekfy.user.domain.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "userSong")
public class UserSong {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSongSequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Column(name = "personalViews")
    private Long personalViews;

    @Column(name = "personalValorations")
    private Long personalValorations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
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
