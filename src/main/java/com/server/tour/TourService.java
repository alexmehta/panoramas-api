package com.server.tour;

import com.jlefebure.spring.boot.minio.MinioException;
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

    public Tour createTour(String name, String description, List<MultipartFile> photos) throws MinioException, IOException {
        Tour tour = new Tour(name, description);
        for (MultipartFile photo : photos) {
            tour.getPanoramaFrames().add(panoramaService.createPanorama(name + "/" + photo.getOriginalFilename(), photo));
        }
        return tour;
    }
    public List<Tour> fetchAll(int amount){
        var list  =tourRepo.fetchAll();
        if (amount > 0) {
            return list.subList(0,Math.min(amount,list.size()));
        }
        return list;
    }
    public Tour insert(String name, String description, List<MultipartFile> folder) throws MinioException, IOException {
        return createTour(name,description,folder);
    }
}
