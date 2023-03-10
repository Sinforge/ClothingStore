package ru.sinforge.clothingstore.DTOs;

import jakarta.persistence.ElementCollection;
import ru.sinforge.clothingstore.Entities.Enums.Gender;

import java.util.List;

public class CreateClothDto {

    private String name;

    private String brand_name;

    private String section;

    private Gender gender;

    private String color;

    private List<String> sizes;

    private int price;
}
