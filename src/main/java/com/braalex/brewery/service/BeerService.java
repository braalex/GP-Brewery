package com.braalex.brewery.service;

import com.braalex.brewery.dto.BeerDto;
import com.braalex.brewery.dto.IdResponse;
import com.braalex.brewery.dto.IngredientDto;
import com.braalex.brewery.dto.IngredientType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerService {

    private final List<BeerDto> beers = List.of(BeerDto.builder()
                    .beerId(1L)
                    .type("Stout")
                    .beerName("Espresso Stout")
                    .abv(6.1)
                    .originalGravity(14.0)
                    .description("Coffee stout")
                    .ingredients(List.of(IngredientDto.builder()
                                    .type(IngredientType.HOPS)
                                    .name("Magnum")
                                    .build(),
                            IngredientDto.builder()
                                    .type(IngredientType.MALT)
                                    .name("Brown Malt")
                                    .build(),
                            IngredientDto.builder()
                                    .type(IngredientType.YEAST)
                                    .name("Ale Yeast")
                                    .build()))
                    .price(4.2)
                    .build(),
            BeerDto.builder()
                    .beerId(2L)
                    .type("IPA")
                    .beerName("Madness")
                    .abv(6.6)
                    .originalGravity(13.0)
                    .description("American style IPA")
                    .ingredients(List.of(IngredientDto.builder()
                                    .type(IngredientType.HOPS)
                                    .name("Cascade")
                                    .build(),
                            IngredientDto.builder()
                                    .type(IngredientType.MALT)
                                    .name("Rye Malt")
                                    .build(),
                            IngredientDto.builder()
                                    .type(IngredientType.YEAST)
                                    .name("Yeast")
                                    .build()))
                    .price(5.3)
                    .build());

    public List<BeerDto> getList() {
        return beers;
    }

    public IdResponse createBeer(BeerDto request) {
        return new IdResponse(3);
    }
}
