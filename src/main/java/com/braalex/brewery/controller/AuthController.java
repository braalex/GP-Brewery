package com.braalex.brewery.controller;

import com.braalex.brewery.dto.CustomerDto;
import com.braalex.brewery.dto.StaffDto;
import com.braalex.brewery.dto.UserSignInRequestDto;
import com.braalex.brewery.dto.UserSignInResponseDto;
import com.braalex.brewery.exception.SuchUserAlreadyExistException;
import com.braalex.brewery.service.BrewerService;
import com.braalex.brewery.service.CustomerService;
import com.braalex.brewery.service.DirectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private final BrewerService brewerService;
    private final CustomerService customerService;
    private final DirectorService directorService;

    @PostMapping(value = "/brewers/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponseDto brewerSignUp(@RequestBody final StaffDto staffDtoRequest)
            throws SuchUserAlreadyExistException {
        return brewerService.signUp(staffDtoRequest);
    }

    @PostMapping(value = "/brewers/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResponseDto brewerSignIn(@RequestBody final UserSignInRequestDto userSignInRequest) {
        return brewerService.signIn(userSignInRequest);
    }

    @PostMapping(value = "/customers/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponseDto customerSignUp(@RequestBody final CustomerDto customerDtoRequest)
            throws SuchUserAlreadyExistException {
        return customerService.signUp(customerDtoRequest);
    }

    @PostMapping(value = "/customers/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResponseDto customerSignIn(@RequestBody final UserSignInRequestDto userSignInRequest) {
        return customerService.signIn(userSignInRequest);
    }

    @PostMapping(value = "/director/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignInResponseDto directorSignUp(@RequestBody final StaffDto staffDtoRequest)
            throws SuchUserAlreadyExistException {
        return directorService.signUp(staffDtoRequest);
    }

    @PostMapping(value = "/director/sign-in", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSignInResponseDto directorSignIn(@RequestBody final UserSignInRequestDto userSignInRequest) {
        return directorService.signIn(userSignInRequest);
    }
}
