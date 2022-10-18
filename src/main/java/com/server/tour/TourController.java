package com.server.tour;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController()
public class TourController {


    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }


    @GetMapping("/fetch")
    public List<Tour> fetchAll(){
        return tourService.fetchAll(100);
    }
    @PostMapping("/insert/{name}/{description}")
    public Tour insert(@PathVariable String name, @PathVariable String description, @RequestBody MultipartFile[] files) throws  IOException {
        Long response = tourService.insert(name,description, List.of(files));
        return tourService.getTour(response);
    }
    @GetMapping("/get/image/{id}")

    public byte[][] getImage(@PathVariable Long id){
        return tourService.getImage(id);

    }
    @GetMapping("/get/content/{content}")
    public ResponseEntity<byte[]> getImage(@PathVariable String content){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(tourService.getImage(content));
    }
    @GetMapping("/get/{id}")
    public Tour getTour(@PathVariable Long id){
        return tourService.getTour(id);
    }
    @PostMapping("/inserts")
    public String test(){
        return "Test";
    }


}
