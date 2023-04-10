package ru.sinforge.clothingstore.Services;

import ru.sinforge.clothingstore.Entities.User;

import java.util.List;

public interface BasketService {
    Boolean addToBasket(User user, Long id);

    Boolean payOrder(List<Long> ids, User user);
}
