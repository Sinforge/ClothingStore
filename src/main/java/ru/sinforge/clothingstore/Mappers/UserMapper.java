package ru.sinforge.clothingstore.Mappers;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import ru.sinforge.clothingstore.DTOs.CreateUserDto;
import ru.sinforge.clothingstore.Entities.User;

@Mapper
public interface UserMapper {

    @Mapping(target ="country", source = "country")
    User createUserDtoToUser(CreateUserDto createUserDto);
}
