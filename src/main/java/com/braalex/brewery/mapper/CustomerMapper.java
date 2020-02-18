package com.braalex.brewery.mapper;

import com.braalex.brewery.dto.CustomerDto;
import com.braalex.brewery.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    UserEntity sourceToDestination(CustomerDto source);

    CustomerDto destinationToSource(UserEntity destination);
}
