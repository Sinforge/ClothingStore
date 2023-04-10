package ru.sinforge.clothingstore.Services.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Repositories.ClothRepository;
import ru.sinforge.clothingstore.Services.ClothService;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class ClothServiceImpl implements ClothService {

    private final ClothRepository _clothRepository;
    @Value("${upload.path}")
    private String uploadPath;
    public ClothServiceImpl(ClothRepository clothRepository) {
        _clothRepository = clothRepository;

    }
    @Override
    @Transactional
    public boolean addCloth(Cloth cloth, MultipartFile img) throws NoSuchAlgorithmException {
        if(_clothRepository.existsByName(cloth.getName())) {
            return false;
        }
        _clothRepository.save(cloth);
        if(!img.isEmpty()) {
            try {
                img.transferTo(new File(uploadPath + "/"+ cloth.getName() + cloth.getBrand_name()
                        + "." + Objects.requireNonNull(img.getOriginalFilename()).split("\\.")[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Cloth getClothById(Long id) {
        return _clothRepository.getClothById(id);
    }

    @Override
    public Iterable<Cloth> getAll() {
        return _clothRepository.findAll();
    }
}
