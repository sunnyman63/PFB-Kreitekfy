package com.kreitek.kreitekfy.song.application.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.util.Date;

public class SongDTO {

    private Long id;
    private String name;
    private String duration;
    private Date inclusionDate;
    private Long totalVisualizations;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public Long getTotalVisualizations() {
        return totalVisualizations;
    }

    public void setTotalVisualizations(Long totalVisualizations) {
        this.totalVisualizations = totalVisualizations;
    }
}
