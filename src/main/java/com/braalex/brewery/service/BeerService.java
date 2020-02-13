package com.braalex.brewery.service;

import com.braalex.brewery.dto.BeerDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeerService {

    private final List<BeerDto> beers = new ArrayList<>();

    public List<BeerDto> getBeers() {
        return beers;
    }

    public BeerDto createBeer(final BeerDto request) {
        return null;
//                BeerDto.builder()
//                .id(3L)
//                .type(request.getType())
//                .beerName(request.getBeerName())
//                .abv(request.getAbv())
//                .originalGravity(request.getOriginalGravity())
//                .description(request.getDescription())
//                .ingredients(request.getIngredients())
//                .price(request.getPrice())
//                .build();
    }

    public void deleteBeerById(final Long id) {
        beers.remove(id);
    }
}
