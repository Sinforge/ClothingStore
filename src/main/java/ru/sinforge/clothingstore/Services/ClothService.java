package ru.sinforge.clothingstore.Services;

import org.springframework.web.multipart.MultipartFile;
import ru.sinforge.clothingstore.Entities.Cloth;

import java.security.NoSuchAlgorithmException;

public interface ClothService {
    boolean addCloth(Cloth cloth, MultipartFile img) throws NoSuchAlgorithmException;


    Iterable<Cloth> getAll();
}
