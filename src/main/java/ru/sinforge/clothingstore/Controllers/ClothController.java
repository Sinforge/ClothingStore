package ru.sinforge.clothingstore.Controllers;


import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sinforge.clothingstore.DTOs.CreateClothDto;
import ru.sinforge.clothingstore.DTOs.ReadClothDto;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.User;
import ru.sinforge.clothingstore.Mappers.ClothMapper;
import ru.sinforge.clothingstore.Services.BasketService;
import ru.sinforge.clothingstore.Services.ClothService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/cloth")
public class ClothController {
    @Value("${upload.path}")
    private String path;

    private final ClothService _clothService;

    private final ClothMapper _clothMapper = Mappers.getMapper(ClothMapper.class);
    private final BasketService _basketService;

    public ClothController(ClothService clothService, BasketService basketService)
    {
        _basketService = basketService;
        _clothService = clothService;
    }

   @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("allCloth", _clothMapper
                .arrayClothToArrayReadDto(_clothService.getAll()));
        model.addAttribute("path", path);
        return "get_all_cloth";
    }

    @GetMapping("/image/{name}")
    @ResponseBody
    public byte[] getImage(@PathVariable String name) throws IOException {
        File serverFile = new File(path + "/" + name + ".png");
        return Files.readAllBytes(serverFile.toPath());

    }

    @GetMapping("/{id}")
    @ResponseBody
    public Cloth getClothById(@PathVariable Long id) {
        return _clothService.getClothById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addToBasket/{id}")
    public Boolean addToBasket(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Boolean isGood = false;
        if (user != null) {
            isGood = _basketService.addToBasket(user, id);
        }
        return isGood;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/payOrder")
    public Boolean payOrder(@RequestBody List<Long> ids, @AuthenticationPrincipal User user) {
        return _basketService.payOrder(ids, user);
    }

}
