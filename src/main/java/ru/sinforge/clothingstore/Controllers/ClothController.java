package ru.sinforge.clothingstore.Controllers;


import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sinforge.clothingstore.DTOs.CreateClothDto;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.User;
import ru.sinforge.clothingstore.Mappers.ClothMapper;
import ru.sinforge.clothingstore.Repositories.ClothRepository;
import ru.sinforge.clothingstore.Services.ClothService;

@RestController
public class ClothController {

    private final ClothService _clothService;

    private final ClothMapper _clothMapper = Mappers.getMapper(ClothMapper.class);

    public ClothController(ClothService clothService) {
        _clothService = clothService;
    }
    @PostMapping("/cloth/add")
    @ResponseBody
    public ResponseEntity<Cloth> add(@RequestBody CreateClothDto createClothDto) {
        Cloth cloth = _clothMapper.createClothDtoToCloth(createClothDto);
        if(cloth != null) {
            _clothService.addCloth(cloth);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cloth, HttpStatus.OK);
    }


}
