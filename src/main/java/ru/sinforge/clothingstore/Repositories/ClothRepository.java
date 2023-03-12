package ru.sinforge.clothingstore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sinforge.clothingstore.Entities.Cloth;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
    boolean existsByName(String name);
}
