package ru.sinforge.clothingstore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sinforge.clothingstore.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
