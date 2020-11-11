package com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.mappers;

import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.dtos.UserDTO;
import com.example.springbootkafkamicroservices.springbootnodejskafkamicroservices.models.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    UserDTO userToUserDto(User user);

    @Mapping(target = "id", source = "userDto.id")
    @Mapping(target = "firstName", source = "userDto.firstName")
    @Mapping(target = "lastName", source = "userDto.lastName")
    @Mapping(target = "email", source = "userDto.email")
    User userDtoToUser(UserDTO userDto);
}
