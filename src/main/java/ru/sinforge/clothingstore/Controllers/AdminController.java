package ru.sinforge.clothingstore.Controllers;


import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    private final ClothService _clothService;
    private final ClothMapper _clothMapper = Mappers.getMapper(ClothMapper.class);
    @GetMapping("/create_cloth_page")
    public String createClothPage(Model model) {

        return "create_cloth_page";
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

    @PostMapping("/delete_comment")
    public String DeleteComment(@RequestParam Long commentId, @RequestParam Long clothId) {
        _clothService.deleteComment(commentId);
        return "redirect:/cloth/" + clothId;
    }
    @PostMapping("/delete_page")
    public String DeletePage(@RequestParam Long clothid) {
        _clothService.deletePage(clothid);
        return "redirect:/cloth/all";
    }
    @GetMapping("/change_page")
    public String ChangePage(@RequestParam Long clothid, Model model) {
        model.addAttribute("clothData", _clothService.getClothById(clothid));
        return "edit-cloth-page";


    }
    @PostMapping("/change_page")
    public String SaveChanges(Cloth cloth, @RequestParam MultipartFile img) {
        _clothService.saveChanges(cloth, img);
        return "redirect:/cloth/" + cloth.getId();
    }
}
