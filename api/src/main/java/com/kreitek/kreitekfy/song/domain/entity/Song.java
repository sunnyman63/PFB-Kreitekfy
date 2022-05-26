package com.kreitek.kreitekfy.song.domain.entity;

import com.kreitek.kreitekfy.album.domain.entity.Album;

import com.kreitek.kreitekfy.style.domain.entity.Style;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SongSequence")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @Size(min = 4, max = 100)
    private String name;

    @Column(name = "duration", nullable = false)
    @Min(value = 1)
    private Integer duration;

    @Column(name = "inclusion_date", nullable = false)
    private Date inclusionDate;

    @Column(name = "total_views", nullable = false)
    private Long totalViews;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;
    public Song() {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public Long getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Long totalVisualizations) {
        this.totalViews = totalVisualizations;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
