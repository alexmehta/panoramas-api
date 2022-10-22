package com.server.tour;

import com.fasterxml.jackson.databind.JsonNode;
import com.server.tour.Tour;
import com.server.tour.TourService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;

@RestController()
public class TourController {

    private final TourService tourService;
    private final HashMap<String, String> keyStorage = new HashMap<>();

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping("/process_tour/{string}")
    public String processTour(@RequestBody JsonNode payload, @PathVariable String string) {
        keyStorage.put(string, payload.toString());
        return string;
    }

    @GetMapping("/get_tour{id}")
    public String getTourJson(@PathVariable String id) {
        return keyStorage.get(id);
    }


    @GetMapping("/fetch")
    public List<Tour> fetchAll() {
        return tourService.fetchAll(100);
    }

    @PostMapping("/insert/{name}/{description}")
    public Tour insert(@PathVariable String name, @PathVariable String description, @RequestBody MultipartFile[] files) throws IOException {
        Long response = tourService.insert(name, description, List.of(files));
        return tourService.getTour(response);
    }

    @GetMapping("/get/image/{id}")
    public byte[][] getImage(@PathVariable Long id) {
        return tourService.getImage(id);

    }

    @GetMapping("/get/content/{content}")
    public ResponseEntity<byte[]> getImage(@PathVariable String content) {
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(tourService.getImage(content));
    }

    @GetMapping("/get/{id}")
    public Tour getTour(@PathVariable Long id) {
        return tourService.getTour(id);
    }

    @PostMapping("/inserts")
    public String test() {
        return "Test";
    }


}