package com.server.operations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()

@Tag(name = "", description = "")
public class BaseController {

    final Service Service;

    public BaseController(Service Service) {
        this.Service = Service;
    }

    @Operation(summary = "",
            description = "", tags = ""
    )
    @PostMapping("/find")
    public List<Item> contains(@RequestBody List<String> word) {
        return Service.getWithTag(word);
    }
    @GetMapping("/fetch")
    public List<Item> fetchAll(int amount){
        return Service.fetchAll(amount);
    }
    @Operation(summary = "", description = "",
            responses = {
                    @ApiResponse(description = "", responseCode = "200", content = @Content(mediaType = "application/json"))
                    , @ApiResponse(description = "", responseCode = "404")
            }
    )
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return Service.deleteEC(id);
    }
    @PostMapping("/insert")
    public String insert(@RequestBody Payload payload) {
        return Service.insertEC(payload);
    }
}
