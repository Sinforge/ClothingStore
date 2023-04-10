package ru.sinforge.clothingstore.DTOs;

import lombok.Getter;
import lombok.Setter;
import ru.sinforge.clothingstore.Entities.Enums.Role;

@Getter
@Setter
public class CreateUserDto {

    public String email;
    public String country;
    public String name;
    public String surname;
    public String password;



}
