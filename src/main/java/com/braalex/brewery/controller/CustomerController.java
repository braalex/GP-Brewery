package com.braalex.brewery.controller;

import com.braalex.brewery.dto.CustomerSignInRequest;
import com.braalex.brewery.service.CustomerService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String singUp(@RequestBody final CustomerSignInRequest request) {
        log.info("email = " + request.getEmail());
        log.info("category = " + request.getCategory());
        return customerService.signUp(request);
    }

    @PostMapping(value = "/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String singIn(@RequestBody final CustomerSignInRequest request) {
        log.info("email = " + request.getEmail());
        return customerService.signIn(request);
    }
}
