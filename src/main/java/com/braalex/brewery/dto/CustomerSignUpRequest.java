package com.braalex.brewery.dto;

import lombok.Data;

@Data
public class CustomerSignUpRequest {
    private String email;
    private String password;
    private String category;
    private String companyName;
}
