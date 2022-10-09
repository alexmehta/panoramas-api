package com.server.panorama;

import com.server.tour.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Component
public class PanoramaService {
    @Autowired
    private PanoramaRepository repository;
    @Autowired
    private PhotoStore photoStore;


    public PanoramaFrame createPanorama(Tour t, MultipartFile file) throws IOException {
        PanoramaFrame p = new PanoramaFrame();
        photoStore.setContent(p, file.getInputStream());
        p.setTour(t);
        repository.save(p);
        return p;
    }

}
