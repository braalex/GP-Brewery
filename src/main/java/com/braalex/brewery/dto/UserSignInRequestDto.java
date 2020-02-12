package com.braalex.brewery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignInRequestDto {
    private final String email;
    private final String password;
}
