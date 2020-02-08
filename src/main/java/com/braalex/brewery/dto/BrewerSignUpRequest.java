package com.braalex.brewery.dto;

import lombok.Data;

@Data
public class BrewerSignUpRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
