package com.server.panorama;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Service
public class PanoramaService {
    private final PanoramaRepository repository;
    @Autowired
    private MinioService minioService;

    public PanoramaService(PanoramaRepository repository) {
        this.repository = repository;
    }


    public PanoramaFrame createPanorama(String name, MultipartFile file) throws MinioException, IOException {
        uploadPanorama(name, file);
        PanoramaFrame p = new PanoramaFrame();
        p.setPanorama_url(name);
        return p;
    }

    public void uploadPanorama(String name, MultipartFile file) throws IOException, MinioException {
        minioService.upload(Paths.get(name), file.getInputStream(), file.getContentType());

    }
}
