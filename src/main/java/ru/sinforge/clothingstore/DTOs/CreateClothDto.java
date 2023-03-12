package ru.sinforge.clothingstore.DTOs;

import jakarta.persistence.ElementCollection;
import ru.sinforge.clothingstore.Entities.Enums.Gender;

import java.util.List;

public class CreateClothDto {

    public String name;

    public String brand_name;

    public String section;

    public String gender;

    public String color;

    public List<String> sizes;

    public int price;
}
