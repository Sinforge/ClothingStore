package ru.sinforge.clothingstore.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sinforge.clothingstore.DTOs.Filter;
import ru.sinforge.clothingstore.Entities.Comment;
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
    private final ObjectMapper _objectMapper;
    private final ClothMapper _clothMapper = Mappers.getMapper(ClothMapper.class);
    private final BasketService _basketService;

    public ClothController(ClothService clothService, ObjectMapper objectMapper, BasketService basketService)
    {
        _objectMapper = objectMapper;
        _basketService = basketService;
        _clothService = clothService;
    }

   @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("allSizes", _clothService.getAllSizes());
        model.addAttribute("allBrands", _clothService.getAllBrands());
       model.addAttribute("allSections", _clothService.getAllSections());
       model.addAttribute("allColors", _clothService.getAllColors());

       model.addAttribute("allCloth", _clothMapper
                .arrayClothToArrayReadDto(_clothService.getAll()));
        model.addAttribute("path", path);
        return "get_all_cloth";
    }

    @GetMapping("/image/{name}")
    @ResponseBody
    public byte[] getImage(@PathVariable String name) throws IOException {
        File serverFile = new File(path + "clothImages/" + name + ".png");
        return Files.readAllBytes(serverFile.toPath());

    }

    @PostMapping(value="/all/filtered", produces = { MediaType.APPLICATION_JSON_VALUE})
    public String getFilteredList(@RequestBody Filter filter, Model model) throws JsonProcessingException {
        model.addAttribute("allSizes", _clothService.getAllSizes());
        model.addAttribute("allBrands", _clothService.getAllBrands());
        model.addAttribute("allSections", _clothService.getAllSections());
        model.addAttribute("allColors", _clothService.getAllColors());
        model.addAttribute("allCloth", _clothMapper
               .arrayClothToArrayReadDto(_clothService.filterCloth(filter)));
        model.addAttribute("path", path);
        return "get_all_cloth";
    }

    @GetMapping("/{id}")
    public String getClothById(@PathVariable Long id, Model model) {
        var check = _clothMapper.clothToReadClothDto(_clothService.getClothById(id));
        model.addAttribute("clothData", check);
        model.addAttribute("comments", _clothService.getAllComment(id));
        return "cloth_page";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addToBasket/{id}")
    public String addToBasket(@PathVariable Long id, @RequestParam String color, @RequestParam String size,
                              @AuthenticationPrincipal User user) {
        Boolean isGood = false;
        if (user != null) {
            isGood = _basketService.addToBasket(user, id, color, size);
        }
        return "redirect:/cloth/" + id;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/basket")
    public String getBasket(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("basketData",_basketService.getBasketByUser(user));
        return "basket";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/basket")
    public String payOrder(@RequestParam List<Long> ids, @AuthenticationPrincipal User user) {
        _basketService.payOrder(ids, user);
        return "redirect:/cloth/basket";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/leave")
    public String leaveComment(@RequestParam Long clothid, @RequestParam String text, @AuthenticationPrincipal User user) {
        Comment comment = new Comment();
        comment.setClothid(clothid);
        comment.setText(text);
        comment.setUserid(user.getId());
        comment.setUsername(user.getUsername());
        _clothService.saveComment(comment);
        return "redirect:/cloth/" + clothid;

    }
}
