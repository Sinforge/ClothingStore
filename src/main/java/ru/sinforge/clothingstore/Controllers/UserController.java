package ru.sinforge.clothingstore.Controllers;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sinforge.clothingstore.DTOs.CreateUserDto;
import ru.sinforge.clothingstore.Entities.User;
import ru.sinforge.clothingstore.Mappers.UserMapper;
import ru.sinforge.clothingstore.Services.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class UserController {
    @Value("${upload.path}")
    private String path;

    private final UserMapper _userMapper = Mappers.getMapper(UserMapper.class);
    private final UserService _userService;

    public UserController(UserService service) {
        _userService = service;
    }

    @GetMapping("/registration")
    public String registrationGet() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(CreateUserDto createUserDto) {
        User user = _userMapper.createUserDtoToUser(createUserDto);
        if(!_userService.addUser(user)) {
            return "registration";
        }
        return "login";
    }

    @GetMapping("/image/{name}")
    @ResponseBody
    public byte[] getImage(@PathVariable String name){
        File serverFile = null;
        byte[] bytes = null;
        try {
            serverFile = new File(path + "userAvatars/" + name + ".png");
            bytes = Files.readAllBytes(serverFile.toPath());
        }
        catch (Exception ex) {
            serverFile = new File(path + "userAvatars/anon.png");
            bytes = Files.readAllBytes(serverFile.toPath());
        }
        finally {
            return bytes;
        }

    }
}
