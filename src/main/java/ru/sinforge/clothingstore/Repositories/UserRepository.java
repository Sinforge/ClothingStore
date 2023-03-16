package ru.sinforge.clothingstore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sinforge.clothingstore.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByEmail(String email);
}
