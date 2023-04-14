package ru.sinforge.clothingstore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sinforge.clothingstore.Entities.BasketCloth;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.User;

import java.util.List;

@Repository
public interface BasketClothRepository extends JpaRepository<BasketCloth, Long> {

    @Query(
            value= "SELECT b FROM BasketCloth b WHERE b.cloth.id in :ids and b.user = :user"
    )
    List<BasketCloth> findAllByListIdsAndUser(@Param("ids") List<Long> ids, @Param("user")User user);


    List<BasketCloth> findAllByUser(User user);
}
