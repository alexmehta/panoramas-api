package com.server.tour;
import com.jlefebure.spring.boot.minio.MinioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TourController {


    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/fetch")
    public List<Tour> fetchAll(int amount){
        return tourService.fetchAll(amount);
    }
    @Operation(summary = "", description = "",
            responses = {
                    @ApiResponse(description = "", responseCode = "200", content = @Content(mediaType = "application/json"))
                    , @ApiResponse(description = "", responseCode = "404")
            }
    )
    @PostMapping("/insert/{name}")
    public String insert(@PathVariable String name,@RequestBody TourPayload payload) throws MinioException, IOException {
        return String.valueOf(tourService.insert(name,payload.getDescription(),payload.getFolder()));
    }
}
