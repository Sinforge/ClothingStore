package ru.sinforge.clothingstore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sinforge.clothingstore.Entities.Cloth;

public interface ClothRepository extends JpaRepository<Cloth, Long> {

}
