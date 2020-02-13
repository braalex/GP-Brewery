package com.braalex.brewery.service;

import com.braalex.brewery.dto.BrewDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrewService {

    private final List<BrewDto> brews = new ArrayList<>();

    public List<BrewDto> getBrews() {
        return brews;
    }

    public List<BrewDto> getBrewsByBrewer(final Long id) {
        return brews.stream()
                .filter(brew -> brew.getBrewerId().equals(id))
                .collect(Collectors.toList());
    }

    public BrewDto createBrew(final BrewDto request) {
        return null;
//                BrewDto.builder()
//                .id(3L)
//                .brewerId(request.getBrewerId())
//                .beerId(request.getBeerId())
//                .startDate(request.getStartDate())
//                .endDate(request.getEndDate())
//                .build();
    }

        public BrewDto modifyBrewById(final Long id, final BrewDto request) {
        Optional<BrewDto> brew = brews.stream()
                .filter(b -> b.getId().equals(id))
                .findAny();
        if (brew.isPresent()) {
            if (request.getBrewerId() != null) {
                brew.get().setBrewerId(request.getBrewerId());
            }
            if (request.getBeerId() != null) {
                brew.get().setBeerId(request.getBeerId());
            }
            if (request.getStartDate() != null) {
                brew.get().setStartDate(request.getStartDate());
            }
            if (request.getEndDate() != null) {
                brew.get().setEndDate(request.getEndDate());
            }
            return brew.get();
        }
        return null;
    }
}
