package com.braalex.brewery.service;

import com.braalex.brewery.dto.CustomerSignUpRequest;
import com.braalex.brewery.dto.IdResponse;
import com.braalex.brewery.dto.UserSignInRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public IdResponse signUp(final CustomerSignUpRequest request) {
        return new IdResponse(1);
    }

    public IdResponse signIn(final UserSignInRequest request) {
        return new IdResponse(1);
    }

}
