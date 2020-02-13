package com.braalex.brewery.mapper;

import com.braalex.brewery.dto.BrewDto;
import com.braalex.brewery.entity.BrewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrewMapper {
    BrewEntity sourceToDestination(BrewDto source);

    BrewDto destinationToSource(BrewEntity destination);
}
