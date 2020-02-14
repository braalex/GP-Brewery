package com.braalex.brewery.mock;

import com.braalex.brewery.dto.BeerDto;
import com.braalex.brewery.dto.IngredientDto;
import com.braalex.brewery.dto.IngredientType;

import java.util.List;

public class BeerControllerMockData {

    public static List<BeerDto> getBeerList() {
        return List.of(BeerDto.builder()
                        .id(1L)
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
                        .id(2L)
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
    }

    public static BeerDto getNewBeer() {
        return BeerDto.builder()
                .id(3L)
                .type("Wheat")
                .beerName("Summer")
                .abv(4.5)
                .originalGravity(9.0)
                .description("Belgian style wheat beer")
                .ingredients(List.of(IngredientDto.builder()
                                .type(IngredientType.HOPS)
                                .name("Zatec")
                                .build(),
                        IngredientDto.builder()
                                .type(IngredientType.MALT)
                                .name("Wheat Malt")
                                .build(),
                        IngredientDto.builder()
                                .type(IngredientType.YEAST)
                                .name("Yeast")
                                .build()))
                .price(3.5)
                .build();
    }

    public static BeerDto postNewBeer() {
        return BeerDto.builder()
                .type("Wheat")
                .beerName("Summer")
                .abv(4.5)
                .originalGravity(9.0)
                .description("Belgian style wheat beer")
                .ingredients(List.of(IngredientDto.builder()
                                .type(IngredientType.HOPS)
                                .name("Zatec")
                                .build(),
                        IngredientDto.builder()
                                .type(IngredientType.MALT)
                                .name("Wheat Malt")
                                .build(),
                        IngredientDto.builder()
                                .type(IngredientType.YEAST)
                                .name("Yeast")
                                .build()))
                .price(3.5)
                .build();
    }

    public static BeerDto getBeer() {
        return BeerDto.builder()
                .id(1L)
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
                .build();
    }
}
