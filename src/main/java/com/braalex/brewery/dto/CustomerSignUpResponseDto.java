package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerSignUpResponseDto {
    private long id;
    private String email;
    private String category;
    private String companyName;
}
