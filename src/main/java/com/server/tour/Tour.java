package com.server.tour;

import com.server.panorama.PanoramaFrame;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tours")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 10000)
    private String description;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PanoramaFrame> panoramaFrames = new ArrayList<>();

    @Version
    @Column(name = "version")
    private Integer version;

    public Tour(String name, String description) {
        this.name = name;
        this.description = description;

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

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "version = " + version + ")";
    }
}
