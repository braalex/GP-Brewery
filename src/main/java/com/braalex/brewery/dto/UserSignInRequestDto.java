package com.braalex.brewery.dto;

import lombok.Data;

@Data
public class UserSignInRequestDto {
    private final String email;
    private final String password;
}
