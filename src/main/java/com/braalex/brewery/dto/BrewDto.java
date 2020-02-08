package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BrewDto {
    private long brewId;
    private long brewerId;
    private long beerId;
    private LocalDate startDate;
    private LocalDate endDate;
}
