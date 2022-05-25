package com.kreitek.kreitekfy.song.domain.entity;

import javax.persistence.*;
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

    @Column(name = "duration", nullable = false, length = 8)
    @Size(min = 8, max = 8)
    private String duration;

    @Column(name = "inclusion_date", nullable = false)
    private Date inclusionDate;

    @Column(name = "total_visualizations", nullable = false)
    private Long totalVisualizations;

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
