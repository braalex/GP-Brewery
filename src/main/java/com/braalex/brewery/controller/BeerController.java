package com.braalex.brewery.controller;

import com.braalex.brewery.dto.BeerDto;
import com.braalex.brewery.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BeerController {
    private final BeerService beerService;

    @GetMapping(value = "/beers")
    public List<BeerDto> getBeers() {
        return beerService.getBeers();
    }

    @PostMapping(value = "/director/beers", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDto createBeer(@RequestBody final BeerDto beerDtoRequest) {
        return beerService.createBeer(beerDtoRequest);
    }

    @DeleteMapping(value = "/director/beers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable final Long id) {
        beerService.deleteBeerById(id);
    }

}
