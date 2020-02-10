package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BrewerSignUpResponseDto {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
