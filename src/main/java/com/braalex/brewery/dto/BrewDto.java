package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BrewDto {
    private Long id;
    private Long brewerId;
    private Long beerId;
    private LocalDate startDate;
    private LocalDate endDate;
}
