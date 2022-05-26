package com.kreitek.kreitekfy.user.domain.entity;

import com.kreitek.kreitekfy.userSong.domain.entity.UserSong;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequence")
    private Long id;

    @Column(length = 100, nullable = false)
    @Size(min = 3, max = 100)
    private String name;

    @Column(nullable = false)
    private boolean admin;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserSong> songsData;


    public Set<UserSong> getSongsData() {
        return songsData;
    }

    public void setSongsData(Set<UserSong> songsData) {
        this.songsData = songsData;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}