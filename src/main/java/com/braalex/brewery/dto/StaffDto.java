package com.braalex.brewery.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
