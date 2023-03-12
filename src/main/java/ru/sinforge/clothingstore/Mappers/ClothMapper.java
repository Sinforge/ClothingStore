package ru.sinforge.clothingstore.Mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sinforge.clothingstore.DTOs.CreateClothDto;
import ru.sinforge.clothingstore.Entities.Cloth;

@Mapper(
        componentModel = "spring"
)
@Service
public interface ClothMapper {

    Cloth createClothDtoToCloth(CreateClothDto createClothDto);

}
