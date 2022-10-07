package com.server.panorama;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
@Component
public class PanoramaService {
    @Autowired
    private PanoramaRepository repository;
    @Autowired
    private PhotoStore photoStore;


    public PanoramaFrame createPanorama(MultipartFile file) throws IOException {
        PanoramaFrame p = new PanoramaFrame();
        photoStore.setContent(p,file.getInputStream());
        repository.save(p);
        return p;
    }

}
