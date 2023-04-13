package ru.sinforge.clothingstore.Mappers;


import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.sinforge.clothingstore.DTOs.CreateClothDto;
import ru.sinforge.clothingstore.DTOs.CreateCommentDto;
import ru.sinforge.clothingstore.DTOs.ReadClothDto;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.Comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ClothMapper {


    @Mapping(target = "name", source = "name")
    Cloth createClothDtoToCloth(CreateClothDto createClothDto);


    ReadClothDto clothToReadClothDto(Cloth cloth);
    default String map(List<String> field) {
        return String.join(", ", field);
    }


    @IterableMapping(elementTargetType = ReadClothDto.class)
    Iterable<ReadClothDto> arrayClothToArrayReadDto(Iterable<Cloth> cloth);

    Comment createCommentDtoToComment(CreateCommentDto createCommentDto);
}
