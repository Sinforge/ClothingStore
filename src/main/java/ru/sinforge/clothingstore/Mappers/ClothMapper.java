package ru.sinforge.clothingstore.Mappers;


import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.sinforge.clothingstore.DTOs.CreateClothDto;
import ru.sinforge.clothingstore.DTOs.ReadClothDto;
import ru.sinforge.clothingstore.Entities.Cloth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mapper
public interface ClothMapper {


    @Mapping(target = "name", source = "name")
    Cloth createClothDtoToCloth(CreateClothDto createClothDto);
    ReadClothDto clothToReadClothDto(Cloth cloth);



    @IterableMapping(elementTargetType = ReadClothDto.class)
    Iterable<ReadClothDto> arrayClothToArrayReadDto(Iterable<Cloth> cloth);
}
