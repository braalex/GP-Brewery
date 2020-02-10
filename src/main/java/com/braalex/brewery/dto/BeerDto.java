package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BeerDto {
    private long id;
    private String type;
    private String beerName;
    private double abv;
    private double originalGravity;
    private String description;
    private List<IngredientDto> ingredients;
    private double price;
}
