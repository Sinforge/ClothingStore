package ru.sinforge.clothingstore.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sinforge.clothingstore.DTOs.CreateClothDto;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.User;

@RestController("/cloth")
public class ClothController {
    @GetMapping("/add")
    @ResponseBody
    public Cloth add(CreateClothDto createClothDto) {
        //TODO: logic for add new cloth
        return null;
    }
}
