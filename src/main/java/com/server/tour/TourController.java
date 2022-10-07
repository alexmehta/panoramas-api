package com.server.tour;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController()
@Tag(name = "Tour Controller")
public class TourController {


    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }


    @GetMapping("/fetch")
    public List<Tour> fetchAll(int amount){
        return tourService.fetchAll(amount);
    }
    @PostMapping("/insert/{name}")
    public Tour insert(@PathVariable String name, @RequestBody MultipartFile[] files) throws  IOException {
        Long response = tourService.insert(name,"fn", List.of(files));

        return tourService.getTour(response);
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
