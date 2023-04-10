package ru.sinforge.clothingstore.Services.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sinforge.clothingstore.Entities.BasketCloth;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.User;
import ru.sinforge.clothingstore.Repositories.BasketClothRepository;
import ru.sinforge.clothingstore.Repositories.ClothRepository;
import ru.sinforge.clothingstore.Services.BasketService;
import ru.sinforge.clothingstore.Services.ClothService;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketClothRepository _basketRepo;
    private final ClothRepository _clothRepo;
    public Boolean addToBasket(User user, Long id) {
        Cloth cloth = _clothRepo.getClothById(id);
        if (cloth == null) {
            return false;
        }
        BasketCloth basketCloth = new BasketCloth();
        basketCloth.setCloth(cloth);
        basketCloth.setUser(user);
        _basketRepo.save(basketCloth);
        return true;

    }

    @Override
    public Boolean payOrder(List<Long> ids, User user) {
        var toDelete = _basketRepo.findAllByListIdsAndUser(ids, user) ;
        _basketRepo.deleteAll(toDelete);
        return true;
    }

}
