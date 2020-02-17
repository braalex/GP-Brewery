package com.braalex.brewery.controller;

import com.braalex.brewery.dto.BrewDto;
import com.braalex.brewery.service.BrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BrewController {
    private final BrewService brewService;

    @GetMapping(value = "/director/brews")
    public List<BrewDto> getBrews() {
        return brewService.getBrews();
    }

    @PostMapping(value = "/director/brews", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BrewDto createBrew(@RequestBody final BrewDto brewDtoRequest) {
        return brewService.createBrew(brewDtoRequest);
    }

    @PatchMapping(value = "/director/brews/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BrewDto modifyBrewById(@PathVariable final Long id, @RequestBody final BrewDto brewDtoRequest) {
        return brewService.modifyBrewById(id, brewDtoRequest);
    }

    @GetMapping(value = "/brewers/{id}/brews")
    public List<BrewDto> getBrewsByBrewerId(@PathVariable final Long id) {
        return brewService.getBrewsByBrewerId(id);
    }
}
