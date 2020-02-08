package com.braalex.brewery.service;

import com.braalex.brewery.dto.BrewerSignUpRequest;
import com.braalex.brewery.dto.IdResponse;
import com.braalex.brewery.dto.UserSignInRequest;
import org.springframework.stereotype.Service;

@Service
public class BrewerService {
    public IdResponse signUp(final BrewerSignUpRequest request) {
        return new IdResponse(5);
    }

    public IdResponse signIn(final UserSignInRequest request) {
        return new IdResponse(5);
    }

}
