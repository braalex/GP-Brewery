package com.braalex.brewery.service;

import com.braalex.brewery.dto.IdResponse;
import com.braalex.brewery.dto.UserSignInRequest;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    public IdResponse signIn(final UserSignInRequest request) {
        return new IdResponse(1);
    }
}
