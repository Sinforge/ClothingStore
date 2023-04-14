package ru.sinforge.clothingstore;

import ru.sinforge.clothingstore.Entities.BasketCloth;

import java.util.List;

public class Helper {
    public static int sum(List<BasketCloth> basketCloths) {
        return basketCloths.stream().mapToInt(b -> b.getCloth().getPrice()).sum();
    }
}
