package ru.sinforge.clothingstore.DTOs;

import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.sinforge.clothingstore.Entities.Enums.Gender;

import java.util.List;

@AllArgsConstructor
public class CreateClothDto {


    public String name;

    public String description;

    public String brand_name;

    public String section;

    public String gender;

    public List<String> colors;

    public List<String> sizes;

    public int price;

}
