package com.server.panorama;

import com.server.tour.Tour;
import org.springframework.content.commons.annotations.ContentId;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Panoramas")
public class PanoramaFrame {

    @Column
    public @ContentId UUID contentID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour")
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

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
