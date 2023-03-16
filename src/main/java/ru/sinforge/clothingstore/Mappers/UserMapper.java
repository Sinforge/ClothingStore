package ru.sinforge.clothingstore.Mappers;

import org.mapstruct.Mapper;

import ru.sinforge.clothingstore.DTOs.CreateUserDto;
import ru.sinforge.clothingstore.Entities.User;

@Mapper
public interface UserMapper {
    User createUserDtoToUser(CreateUserDto createUserDto);
}
