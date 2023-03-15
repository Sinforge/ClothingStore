package ru.sinforge.clothingstore.Services.Impl;

import ru.sinforge.clothingstore.Entities.Cloth;

public interface ClothService {
    boolean addCloth(Cloth cloth);


    Iterable<Cloth> getAll();
}
