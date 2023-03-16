package ru.sinforge.clothingstore.Controllers;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.sinforge.clothingstore.DTOs.CreateUserDto;
import ru.sinforge.clothingstore.Entities.User;
import ru.sinforge.clothingstore.Mappers.UserMapper;
import ru.sinforge.clothingstore.Services.UserService;

@Controller
public class UserController {

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
    public String registrationPost(@ModelAttribute CreateUserDto createUserDto) {
        User user = _userMapper.createUserDtoToUser(createUserDto);
        if(_userService.addUser(user)) {
            return "registration";
        }
        return "login";



    }
}
