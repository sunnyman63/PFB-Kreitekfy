package com.kreitek.kreitekfy.song.application.dto;

import com.kreitek.kreitekfy.artist.domain.entity.Artist;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Date;

public class SongDTO {

    private Long id;
    private String name;
    private Integer duration;
    private Date inclusionDate;
    private Long totalViews;
    private Long totalRate;
    private Long albumId;
    private String albumName;
    private byte[] albumImage;
    private Long styleId;
    private String styleName;
    private String artistId;
    private String artistName;

    public SongDTO() {
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

    public void setTotalViews(Long totalViews) {
        this.totalViews = totalViews;
    }

    public Long getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(Long totalRate) {
        this.totalRate = totalRate;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public byte[] getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(byte[] albumImage) {
        this.albumImage = albumImage;
    }

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
