package ru.sinforge.clothingstore.Services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.sinforge.clothingstore.Entities.User;

public interface UserService extends UserDetailsService {
    boolean addUser(User user);
}
