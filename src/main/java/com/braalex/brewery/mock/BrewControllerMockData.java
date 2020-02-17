package com.braalex.brewery.mock;

import com.braalex.brewery.dto.BrewDto;

import java.time.LocalDate;
import java.util.List;

public class BrewControllerMockData {

    public static List<BrewDto> getBrewsByBrewer() {
        return List.of(BrewDto.builder()
                .id(1L)
                .brewerId(5L)
                .beerId(2L)
                .startDate(LocalDate.of(2020, 2, 10))
                .endDate(LocalDate.of(2020, 3, 25))
                .build());
    }

    public static List<BrewDto> getBrewList() {
        return List.of(BrewDto.builder()
                        .id(1L)
                        .brewerId(5L)
                        .beerId(2L)
                        .startDate(LocalDate.of(2020, 2, 10))
                        .endDate(LocalDate.of(2020, 3, 25))
                        .build(),
                BrewDto.builder()
                        .id(2L)
                        .brewerId(3L)
                        .beerId(1L)
                        .startDate(LocalDate.of(2020, 2, 20))
                        .endDate(LocalDate.of(2020, 4, 20))
                        .build());
    }

    public static BrewDto getNewBrew() {
        return BrewDto.builder()
                .id(3L)
                .brewerId(5L)
                .beerId(3L)
                .startDate(LocalDate.of(2020, 3, 5))
                .endDate(LocalDate.of(2020, 5, 12))
                .build();
    }

    public static BrewDto postNewBrew() {
        return BrewDto.builder()
                .brewerId(5L)
                .beerId(3L)
                .startDate(LocalDate.of(2020, 3, 5))
                .endDate(LocalDate.of(2020, 5, 12))
                .build();
    }

    public static BrewDto getBrew() {
        return BrewDto.builder()
                .id(2L)
                .brewerId(3L)
                .beerId(1L)
                .startDate(LocalDate.of(2020, 3, 1))
                .endDate(LocalDate.of(2020, 5, 1))
                .build();
    }

    public static BrewDto getBrewForModifying() {
        return BrewDto.builder()
                .brewerId(null)
                .beerId(null)
                .startDate(LocalDate.of(2020, 3, 1))
                .endDate(LocalDate.of(2020, 5, 1))
                .build();
    }
}
