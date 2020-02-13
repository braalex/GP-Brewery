package com.braalex.brewery.mapper;

import com.braalex.brewery.dto.IngredientDto;
import com.braalex.brewery.entity.IngredientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientEntity sourceToDestination(IngredientDto source);

    IngredientDto destinationToSource(IngredientEntity destination);
}
