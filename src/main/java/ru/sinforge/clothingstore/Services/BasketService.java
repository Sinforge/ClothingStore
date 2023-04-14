package ru.sinforge.clothingstore.Services;

import ru.sinforge.clothingstore.Entities.BasketCloth;
import ru.sinforge.clothingstore.Entities.User;

import java.util.List;

public interface BasketService {
    Boolean addToBasket(User user, Long id, String color, String size);

    Boolean payOrder(List<Long> ids, User user);


    List<BasketCloth> getBasketByUser(User user);
}
