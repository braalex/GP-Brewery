package com.braalex.brewery.mapper;

import com.braalex.brewery.dto.BeerDto;
import com.braalex.brewery.entity.BeerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeerMapper {
    BeerEntity sourceToDestination(BeerDto source);

    BeerDto destinationToSource(BeerEntity destination);
}
