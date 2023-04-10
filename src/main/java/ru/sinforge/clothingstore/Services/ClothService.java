package ru.sinforge.clothingstore.Services;

import org.springframework.web.multipart.MultipartFile;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.User;

import java.security.NoSuchAlgorithmException;

public interface ClothService {
    boolean addCloth(Cloth cloth, MultipartFile img) throws NoSuchAlgorithmException;

    Cloth getClothById(Long id);

    Iterable<Cloth> getAll();

}
