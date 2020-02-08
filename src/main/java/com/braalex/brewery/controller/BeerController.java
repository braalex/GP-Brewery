package com.braalex.brewery.controller;

import com.braalex.brewery.dto.BeerDto;
import com.braalex.brewery.service.BeerService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@Data
@RestController
@RequestMapping("/beers")
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public List<BeerDto> getList() {
        log.info("Number of beers: " + beerService.getList().size());
        log.info("1: " + beerService.getList().get(0).getBeerName());
        log.info("2: " + beerService.getList().get(1).getBeerName());
        return beerService.getList();
    }
}
