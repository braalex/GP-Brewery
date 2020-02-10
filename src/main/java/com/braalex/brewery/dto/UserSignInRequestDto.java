package com.braalex.brewery.dto;

import lombok.Data;

@Data
public class UserSignInRequestDto {
    private String email;
    private String password;
}
