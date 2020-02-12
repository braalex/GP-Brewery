package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BeerDto {
    private Long id;
    private String type;
    private String beerName;
    private Double abv;
    private Double originalGravity;
    private String description;
    private List<IngredientDto> ingredients;
    private Double price;
}
