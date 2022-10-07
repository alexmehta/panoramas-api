package com.server.tour;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class TourPayload {
    private String description;
    private MultipartFile[] folder;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getFolder() {
        return folder;
    }

    public void setFolder(MultipartFile[] folder) {
        this.folder = folder;
    }

    public TourPayload(String description, MultipartFile[] folder) {
        this.description = description;
        this.folder = folder;
    }
}
