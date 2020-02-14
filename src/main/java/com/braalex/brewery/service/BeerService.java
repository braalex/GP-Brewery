package com.braalex.brewery.service;

import com.braalex.brewery.dto.BeerDto;
import com.braalex.brewery.mapper.BeerMapper;
import com.braalex.brewery.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    public List<BeerDto> getBeers() {
        return beerRepository.findAll().stream()
                .map(beerMapper::destinationToSource)
                .collect(Collectors.toList());
    }

    public BeerDto createBeer(final BeerDto request) {
        BeerDto beer = BeerDto.builder()
                .type(request.getType())
                .beerName(request.getBeerName())
                .abv(request.getAbv())
                .originalGravity(request.getOriginalGravity())
                .description(request.getDescription())
                .ingredients(request.getIngredients())
                .price(request.getPrice())
                .build();
        beerRepository.save(beerMapper.sourceToDestination(beer));
        return beer;

    }

    public void deleteBeerById(final Long id) {
        beerRepository.deleteById(id);
    }
}
