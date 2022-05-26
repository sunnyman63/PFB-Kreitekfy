package com.kreitek.kreitekfy.artist.domain.entity;

import com.kreitek.kreitekfy.album.domain.entity.Album;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ArtistSequence")
    private Long id;

    @Column(length = 100, nullable = false)
    @Size(min = 4, max = 100)
    private String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    Set<Album> albums;

    public Artist() {
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

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
