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

    public List<BrewDto> getBrewsByBrewerId(final Long id) {
        return brewRepository.findAllByBrewerId(id).stream()
                .map(brewMapper::destinationToSource)
                .collect(Collectors.toList());
    }

    public BrewDto createBrew(final BrewDto brewDtoRequest) {
        final BrewDto brew = BrewDto.builder()
                .brewerId(brewDtoRequest.getBrewerId())
                .beerId(brewDtoRequest.getBeerId())
                .startDate(brewDtoRequest.getStartDate())
                .endDate(brewDtoRequest.getEndDate())
                .build();
        brewRepository.save(brewMapper.sourceToDestination(brew));
        return brew;
    }

    public BrewDto modifyBrewById(final Long id, final BrewDto brewDtoRequest) {
        final Optional<BrewDto> brew = brewRepository.findById(id)
                .map(brewMapper::destinationToSource);
        if (brew.isPresent()) {
            if (brewDtoRequest.getBrewerId() != null) {
                brew.get().setBrewerId(brewDtoRequest.getBrewerId());
            }
            if (brewDtoRequest.getBeerId() != null) {
                brew.get().setBeerId(brewDtoRequest.getBeerId());
            }
            if (brewDtoRequest.getStartDate() != null) {
                brew.get().setStartDate(brewDtoRequest.getStartDate());
            }
            if (brewDtoRequest.getEndDate() != null) {
                brew.get().setEndDate(brewDtoRequest.getEndDate());
            }
            return brew.get();
        }
        return null;
    }
}
