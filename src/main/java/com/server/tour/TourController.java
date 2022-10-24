package com.server.tour;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.server.tour.Tour;
import com.server.tour.TourService;
import lombok.SneakyThrows;
import org.springframework.boot.jackson.JsonObjectDeserializer;
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

    @PostMapping(value = "/process_tour/{string}", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String processTour(@RequestBody String payload, @PathVariable String string) {
        keyStorage.put(string, payload.toString());
        return string;
    }

    @SneakyThrows
    @GetMapping("/get_tour/{id}")
    public JsonNode getTourJson(@PathVariable String id) {
        System.out.println(keyStorage.get(id));
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(keyStorage.get((id)));
        return node;
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