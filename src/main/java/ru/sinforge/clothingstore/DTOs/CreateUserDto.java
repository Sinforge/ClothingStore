package ru.sinforge.clothingstore.DTOs;

import lombok.Getter;
import lombok.Setter;
import ru.sinforge.clothingstore.Entities.Enums.Role;


public class CreateUserDto {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String surname;
    @Getter
    @Setter
    private String password;



}
