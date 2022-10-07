package com.server.tour;

import com.server.panorama.PanoramaService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class TourService {
    private final TourRepo tourRepo;
    private final PanoramaService panoramaService;

    public TourService(TourRepo tourRepo, PanoramaService panoramaService) {
        this.tourRepo = tourRepo;
        this.panoramaService = panoramaService;
    }

    public Long createTour(String name, String description, List<MultipartFile> photos) throws  IOException {
        Tour tour = new Tour(name, description);
        for (MultipartFile photo : photos) {
            tour.getPanoramaFrames().add(panoramaService.createPanorama(photo));
        }
        tourRepo.save(tour);
        System.out.println(tour.getPanoramaFrames());
        return tour.getId();
    }
    public List<Tour> fetchAll(int amount){
        return tourRepo.fetchAll();
    }
    public Long insert(String name, String description, List<MultipartFile> folder) throws  IOException {
       return createTour(name,description,folder);

    }

    public Tour getTour(Long id) {
        return tourRepo.getById(id);
    }
}
