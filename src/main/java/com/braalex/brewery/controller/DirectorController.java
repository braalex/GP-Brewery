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
    public UserSignInResponseDto signIn(@RequestBody final UserSignInRequestDto request) {
        log.info("email = " + request.getEmail());
        return directorService.signIn(request);
    }

    @GetMapping(value = "/orders")
    public List<OrderDto> getOrders() {
        List<OrderDto> orderList = orderService.getOrders();
        log.info("Number of beers: " + orderList.size());
        return orderList;
    }

    @GetMapping(value = "/brews")
    public List<BrewDto> getBrews() {
        List<BrewDto> brewList = brewService.getBrews();
        log.info("Number of brews: " + brewList.size());
        return brewList;
    }

    @PostMapping(value = "/beers", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDto createBeer(@RequestBody final BeerDto request) {
        log.info("New beer: " + request.getBeerName());
        return beerService.createBeer(request);
    }

    @PostMapping(value = "/brews", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BrewDto createBrew(@RequestBody final BrewDto request) {
        log.info("New brew start date: " + request.getStartDate());
        return brewService.createBrew(request);
    }

    @DeleteMapping(value = "/beers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerById(@PathVariable long id) {
        beerService.deleteBeerById(id);
        log.info("Beer #" + id + " deleted");
    }

    @PatchMapping(value = "/brews/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BrewDto modifyBrewById(@PathVariable long id, @RequestBody final BrewDto request) {
        log.info("Brew #" + id + " modified");
        return brewService.modifyBrewById(id, request);
    }
}
