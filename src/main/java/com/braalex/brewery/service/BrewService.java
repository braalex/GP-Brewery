package com.braalex.brewery.service;

import com.braalex.brewery.dto.BrewDto;
import com.braalex.brewery.dto.IdResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BrewService {

    private final List<BrewDto> brews = List.of(BrewDto.builder()
                    .brewId(1L)
                    .brewerId(5L)
                    .beerId(2L)
                    .startDate(LocalDate.of(2020, 2, 10))
                    .endDate(LocalDate.of(2020, 3, 25))
                    .build(),
            BrewDto.builder()
                    .brewId(2L)
                    .brewerId(3L)
                    .beerId(1L)
                    .startDate(LocalDate.of(2020, 2, 20))
                    .endDate(LocalDate.of(2020, 4, 20))
                    .build());

    public List<BrewDto> getList() {
        return brews;
    }

    public IdResponse createBrew(BrewDto request) {
        return new IdResponse(3);
    }
}
