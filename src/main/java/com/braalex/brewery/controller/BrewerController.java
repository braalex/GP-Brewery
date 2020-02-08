package com.braalex.brewery.controller;

import com.braalex.brewery.dto.BrewDto;
import com.braalex.brewery.dto.BrewerSignUpRequest;
import com.braalex.brewery.dto.IdResponse;
import com.braalex.brewery.dto.UserSignInRequest;
import com.braalex.brewery.service.BrewService;
import com.braalex.brewery.service.BrewerService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Log
@Data
@RestController
@RequestMapping("/brewers")
public class BrewerController {

    private final BrewerService brewerService;
    private final BrewService brewService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public IdResponse signUp(@RequestBody final BrewerSignUpRequest request) {
        log.info("email = " + request.getEmail());
        log.info("DOB = " + request.getDateOfBirth());
        return brewerService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public IdResponse signIn(@RequestBody final UserSignInRequest request) {
        log.info("email = " + request.getEmail());
        return brewerService.signIn(request);
    }

    @GetMapping(value = "/5/brews")
    public List<BrewDto> getList() {
        List<BrewDto> brewList = brewService.getList()
                .stream()
                .filter(brew -> brew.getBrewerId() == 5)
                .collect(Collectors.toList());
        log.info("Start date: " + brewList.get(0).getStartDate());
        log.info("End date: " + brewList.get(0).getEndDate());
        return brewList;
    }
}
