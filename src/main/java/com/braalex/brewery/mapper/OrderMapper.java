package com.braalex.brewery.mapper;

import com.braalex.brewery.dto.OrderDto;
import com.braalex.brewery.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity sourceToDestination(OrderDto source);

    OrderDto destinationToSource(OrderEntity destination);
}
