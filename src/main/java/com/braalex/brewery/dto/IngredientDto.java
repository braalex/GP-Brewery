package com.braalex.brewery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDto {
    private IngredientType type;
    private String name;
}
