package ru.sinforge.clothingstore.Controllers;


import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sinforge.clothingstore.DTOs.CreateClothDto;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Mappers.ClothMapper;
import ru.sinforge.clothingstore.Services.ClothService;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ClothService _clothService;
    private final ClothMapper _clothMapper = Mappers.getMapper(ClothMapper.class);
    @GetMapping("/create_cloth_page")
    public String createClothPage(Model model) {

        return "cloth_page";
    }

    // TODO : idk why my CreateClothDto object dont mapping by Spring Controller - need to fix
    @PostMapping(path="/create_cloth_page")
    public String createClothPage(@RequestParam String name,
                                  @RequestParam String description, @RequestParam String brand_name,
                                  @RequestParam String section, @RequestParam String gender,
                                  @RequestParam List<String> colors, @RequestParam List<String> sizes,
                                  @RequestParam int price, @RequestParam MultipartFile img
    ) throws NoSuchAlgorithmException {
        CreateClothDto createClothDto = new CreateClothDto(name, description, brand_name, section, gender, colors, sizes, price);
        Cloth cloth = _clothMapper.createClothDtoToCloth(createClothDto);
        if(cloth != null) {
            _clothService.addCloth(cloth, img);
        }
        else {
            return "redirect:/";
        }
        return "redirect:/cloth/all";
    }
}
