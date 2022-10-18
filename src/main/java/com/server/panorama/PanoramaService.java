package com.server.panorama;

import com.server.tour.Tour;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Component
public class PanoramaService {
    @Autowired
    private PanoramaRepository repository;
    @Autowired
    private PhotoStore photoStore;

    @SneakyThrows
    public byte[] getImage(PanoramaFrame p ) {
       return IOUtils.toByteArray(photoStore.getResource(p.getContentID()).getInputStream());
    }
    @SneakyThrows
    public byte[] getImage(String p ) {
        return IOUtils.toByteArray(photoStore.getResource(UUID.fromString(p)).getInputStream());
    }
    public PanoramaFrame createPanorama(Tour t, MultipartFile file) throws IOException {
        PanoramaFrame p = new PanoramaFrame();
        photoStore.setContent(p, file.getInputStream());
        p.setTour(t);
        repository.save(p);

        return p;
    }

}
