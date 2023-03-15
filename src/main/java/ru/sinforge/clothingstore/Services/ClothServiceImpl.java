package ru.sinforge.clothingstore.Services;

import org.springframework.stereotype.Service;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Repositories.ClothRepository;
import ru.sinforge.clothingstore.Services.Impl.ClothService;

@Service
public class ClothServiceImpl implements ClothService {
    private final ClothRepository _clothRepository;

    public ClothServiceImpl(ClothRepository clothRepository) {
        _clothRepository = clothRepository;

    }
    @Override
    public boolean addCloth(Cloth cloth) {
        if(_clothRepository.existsByName(cloth.getName())) {
            return false;
        }
        _clothRepository.save(cloth);
        return true;
    }

    @Override
    public Iterable<Cloth> getAll() {
        return _clothRepository.findAll();
    }
}
