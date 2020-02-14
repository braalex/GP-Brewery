package com.braalex.brewery.service;

import com.braalex.brewery.dto.BrewDto;
import com.braalex.brewery.mapper.BrewMapper;
import com.braalex.brewery.repository.BrewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrewService {
    private final BrewRepository brewRepository;
    private final BrewMapper brewMapper;

    public List<BrewDto> getBrews() {
        return brewRepository.findAll().stream()
                .map(brewMapper::destinationToSource)
                .collect(Collectors.toList());
    }

    public List<BrewDto> getBrewsByBrewer(final Long id) {
        return brewRepository.findAllByBrewerId(id).stream()
                .map(brewMapper::destinationToSource)
                .collect(Collectors.toList());
    }

    public BrewDto createBrew(final BrewDto request) {
        BrewDto brew = BrewDto.builder()
                .brewerId(request.getBrewerId())
                .beerId(request.getBeerId())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
        brewRepository.save(brewMapper.sourceToDestination(brew));
        return brew;
    }

    public BrewDto modifyBrewById(final Long id, final BrewDto request) {
        Optional<BrewDto> brew = brewRepository.findById(id)
                .map(brewMapper::destinationToSource);
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
