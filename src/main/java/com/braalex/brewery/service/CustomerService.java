package com.braalex.brewery.service;

import com.braalex.brewery.dto.*;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public CustomerSignUpResponseDto signUp(final CustomerSignUpRequestDto request) {
        return CustomerSignUpResponseDto.builder()
                .id(1L)
                .email(request.getEmail())
                .category(request.getCategory())
                .companyName(request.getCompanyName())
                .build();
    }

    public UserSignInResponseDto signIn(final UserSignInRequestDto request) {
        return UserSignInResponseDto.builder()
                .id(1L)
                .email(request.getEmail())
                .build();
    }

}
