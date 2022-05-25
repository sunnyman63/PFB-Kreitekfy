package com.kreitek.kreitekfy.album.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySequence")
    private Long id;

    @Column(length = 100, nullable = false)
    @Size(min = 4,max = 100)
    private String name;

    @Lob
    private byte[] image;

    //To do
    //Relation with song

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
