package com.server.panorama;

import com.google.api.client.util.IOUtils;
import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class PanoramaService {
    private final PanoramaRepository repository;
    @Autowired
    private MinioService minioService;

    public PanoramaService(PanoramaRepository repository) {
        this.repository = repository;
    }

    public void getObject(String object, HttpServletResponse response) throws IOException, MinioException {
        InputStream inputStream = minioService.get(Path.of(object));
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        // Set the content type and attachment header.
        response.addHeader("Content-disposition", "attachment;filename=" + object);
        response.setContentType(URLConnection.guessContentTypeFromName(object));
        // Copy the stream to the response's output stream.
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    @PostMapping("/add")
    public void addAttachment(MultipartFile file) {
        Path path = Path.of(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            minioService.upload(path, file.getInputStream(), file.getContentType());
        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new IllegalStateException("The file cannot be read", e);
        }
    }

    public PanoramaFrame createPanorama(String name, MultipartFile file) {
        uploadPanorama(name, file);
        PanoramaFrame p = new PanoramaFrame();
        p.setPanorama_url(name);
        return p;
    }

    public void uploadPanorama(String name, MultipartFile file) {
        try {
            minioService.upload(Paths.get(name), file.getInputStream(), file.getContentType());
        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new IllegalStateException("The file cannot be read", e);
        }
    }
}
