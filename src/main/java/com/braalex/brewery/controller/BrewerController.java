package com.braalex.brewery.controller;

import com.braalex.brewery.dto.*;
import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import com.braalex.brewery.service.BrewService;
import com.braalex.brewery.service.BrewerService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@Data
@RestController
@RequestMapping("/brewers")
public class BrewerController {

    private final BrewerService brewerService;
    private final BrewService brewService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BrewerSignUpResponseDto signUp(@RequestBody final BrewerSignUpRequestDto request)
            throws SuchUserAlreadyExistException {
        log.info("email = " + request.getEmail());
        log.info("DOB = " + request.getDateOfBirth());
        return brewerService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResponseDto signIn(@RequestBody final UserSignInRequestDto request) {
        log.info("email = " + request.getEmail());
        return brewerService.signIn(request);
    }

    @GetMapping(value = "/{id}/brews")
    public List<BrewDto> getBrews(@PathVariable final long id) {
        List<BrewDto> brewList = brewService.getBrewsByBrewer(id);
        log.info("Start date: " + brewList.get(0).getStartDate());
        log.info("End date: " + brewList.get(0).getEndDate());
        return brewList;
    }
}
