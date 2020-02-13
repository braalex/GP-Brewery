package com.braalex.brewery.mapper;

import com.braalex.brewery.dto.CustomerDto;
import com.braalex.brewery.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity sourceToDestination(CustomerDto source);

    CustomerDto destinationToSource(CustomerEntity destination);
}
