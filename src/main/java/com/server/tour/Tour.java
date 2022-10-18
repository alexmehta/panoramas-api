package com.server.tour;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.server.panorama.PanoramaFrame;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity

@javax.persistence.Table(name = "Tours")
public class Tour {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "panos")
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PanoramaFrame> panoramaFrames = new ArrayList<>();

    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", panoramaFrames=" + panoramaFrames +
                ", version=" + version +
                ", id=" + id +
                '}';
    }

    @Version
    @Column(name = "version")
    private Integer version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    public void setVersion(Integer version) {
        this.version = version;
    }

    public Tour(String name, String description) {
        this.name = name;
        this.description = description;

    }

    public Tour() {

    }

    public Integer getVersion() {
        return version;
    }
    public List<PanoramaFrame> getPanoramaFrames() {
        return panoramaFrames;
    }

    public void setPanoramaFrames(List<PanoramaFrame> panoramaFrames) {
        this.panoramaFrames = panoramaFrames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tour tour = (Tour) o;
        return id != null && Objects.equals(id, tour.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
