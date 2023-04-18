package ru.sinforge.clothingstore.Services.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sinforge.clothingstore.Configs.SecurityConfig;
import ru.sinforge.clothingstore.Entities.Enums.Role;
import ru.sinforge.clothingstore.Entities.User;
import ru.sinforge.clothingstore.Repositories.UserRepository;
import ru.sinforge.clothingstore.Services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository _userRepository;



    public UserServiceImpl(UserRepository userRepository) {

        _userRepository = userRepository;
    }
    @Override
    public boolean addUser(User user) {
        if(_userRepository.getByEmail(user.getEmail()) != null) {
            return false;
        }
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRole(Role.USER);
        _userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return _userRepository.getByEmail(username);
    }
}
