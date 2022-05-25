package com.kreitek.kreitekfy.musica.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ArtistSequence")
    private Long id;

    @Column(length = 100, nullable = false)
    @Size(min = 4, max = 100)
    private String name;

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
}
