package com.braalex.brewery.controller;

import com.braalex.brewery.dto.BeerDto;
import com.braalex.brewery.service.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log
@RequiredArgsConstructor
@RestController
@RequestMapping("/beers")
public class BeerController {
    private final BeerService beerService;

    @GetMapping
    public List<BeerDto> getList() {
        List<BeerDto> beerList = beerService.getBeers();
        log.info("Number of beers: " + beerList.size());
        log.info("1: " + beerList.get(0).getBeerName());
        log.info("2: " + beerList.get(1).getBeerName());
        return beerList;
    }
}
