package com.server.panorama;

import com.server.tour.Tour;

import javax.persistence.*;

@Entity
@Table(name = "Panoramas")
public class PanoramaFrame {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour")
    private Tour tour;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "panorama_url", nullable = false, unique = true)
    private String panorama_url;

    public String getPanorama_url() {
        return panorama_url;
    }

    public void setPanorama_url(String panorama_url) {
        this.panorama_url = panorama_url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
