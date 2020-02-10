package com.braalex.brewery.service;

import com.braalex.brewery.dto.BrewDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrewService {

    private final List<BrewDto> brews = List.of(BrewDto.builder()
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

    public List<BrewDto> getBrews() {
        return brews;
    }

    public List<BrewDto> getBrewsByBrewer(final long id) {
        return brews.stream()
                .filter(brew -> brew.getBrewerId() == id)
                .collect(Collectors.toList());
    }

    public BrewDto createBrew(final BrewDto request) {
        return BrewDto.builder()
                .id(3L)
                .brewerId(request.getBrewerId())
                .beerId(request.getBeerId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }
}
