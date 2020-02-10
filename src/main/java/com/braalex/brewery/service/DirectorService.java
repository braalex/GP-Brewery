package com.braalex.brewery.service;

import com.braalex.brewery.dto.UserSignInRequestDto;
import com.braalex.brewery.dto.UserSignInResponseDto;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    public UserSignInResponseDto signIn(final UserSignInRequestDto request) {
        return UserSignInResponseDto.builder()
                .id(1L)
                .email(request.getEmail())
                .build();
    }
}
