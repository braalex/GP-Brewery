package com.braalex.brewery.mapper;

import com.braalex.brewery.dto.StaffDto;
import com.braalex.brewery.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    UserEntity sourceToDestination(StaffDto source);

    StaffDto destinationToSource(UserEntity destination);
}
