package com.braalex.brewery.service;

import com.braalex.brewery.dto.*;
import org.springframework.stereotype.Service;

@Service
public class BrewerService {
    public BrewerSignUpResponseDto signUp(final BrewerSignUpRequestDto request) {
        return BrewerSignUpResponseDto.builder()
                .id(5L)
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }

    public UserSignInResponseDto signIn(final UserSignInRequestDto request) {
        return UserSignInResponseDto.builder()
                .id(5L)
                .email(request.getEmail())
                .build();
    }

}
