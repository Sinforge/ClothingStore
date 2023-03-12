package ru.sinforge.clothingstore.Mappers.Impl;

import ru.sinforge.clothingstore.DTOs.CreateClothDto;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.Enums.Gender;
import ru.sinforge.clothingstore.Mappers.ClothMapper;

public class ClothMapperImpl implements ClothMapper {
    @Override
    public Cloth createClothDtoToCloth(CreateClothDto createClothDto) {
        Cloth cloth = new Cloth();
        cloth.setName(createClothDto.name);
        cloth.setBrand_name(createClothDto.brand_name);
        cloth.setPrice(createClothDto.price);
        String gender = createClothDto.gender.toLowerCase();
        if(gender.equals("unisex")) {
            cloth.setGender(Gender.Unisex);
        } else if (gender.equals("female")) {
            cloth.setGender(Gender.Female);
        }else if(gender.equals("male")) {
            cloth.setGender(Gender.Male);
        }
        cloth.setColor(createClothDto.color);
        cloth.setSizes(createClothDto.sizes);
        return cloth;
    }
}
