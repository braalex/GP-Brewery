package com.braalex.brewery.controller;

import com.braalex.brewery.dto.*;
import com.braalex.brewery.service.BeerService;
import com.braalex.brewery.service.BrewService;
import com.braalex.brewery.service.DirectorService;
import com.braalex.brewery.service.OrderService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@Data
@RestController
@RequestMapping("/director")
public class DirectorController {

    private final DirectorService directorService;
    private final OrderService orderService;
    private final BrewService brewService;
    private final BeerService beerService;

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public IdResponse signIn(@RequestBody final UserSignInRequest request) {
        log.info("email = " + request.getEmail());
        return directorService.signIn(request);
    }

    @GetMapping(value = "/orders")
    public List<OrderDto> getOrderList() {
        List<OrderDto> orderList = orderService.getList();
        log.info("Number of beers: " + orderList.size());
        return orderList;
    }

    @GetMapping(value = "/brews")
    public List<BrewDto> getBrewList() {
        List<BrewDto> brewList = brewService.getList();
        log.info("Number of brews: " + brewList.size());
        return brewList;
    }

    @PostMapping(value = "/beers", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createBeer(@RequestBody final BeerDto request) {
        log.info("New beer: " + request.getBeerName());
        return beerService.createBeer(request);
    }

    @PostMapping(value = "/brews", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse createBrew(@RequestBody final BrewDto request) {
        log.info("New brew start date: " + request.getStartDate());
        return brewService.createBrew(request);
    }
}
