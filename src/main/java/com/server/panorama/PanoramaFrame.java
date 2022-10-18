package com.server.panorama;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.server.tour.Tour;
import org.springframework.content.commons.annotations.ContentId;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Panoramas")
public class PanoramaFrame {

    @Column
    public @ContentId UUID contentID;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "tour")

    @JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tour tour;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PanoramaFrame{" + ", contentID=" + contentID + ", id=" + id + '}';
    }

    public Tour getTour() {
        return tour;
    }

    public UUID getContentID() {
        return contentID;
    }

    public void setContentID(UUID contentID) {
        this.contentID = contentID;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
