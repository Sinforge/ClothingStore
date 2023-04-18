package ru.sinforge.clothingstore.Services.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.sinforge.clothingstore.DTOs.Filter;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.Comment;
import ru.sinforge.clothingstore.Repositories.ClothRepository;
import ru.sinforge.clothingstore.Repositories.CommentRepository;
import ru.sinforge.clothingstore.Services.ClothService;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClothServiceImpl implements ClothService {

    private final ClothRepository _clothRepository;
    private final CommentRepository _commentRepository;
    @Value("${upload.path}")
    private String uploadPath;
    public ClothServiceImpl(ClothRepository clothRepository, CommentRepository commentRepository) {
        _clothRepository = clothRepository;
        _commentRepository = commentRepository;

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

    @Override
    @Transactional
    public boolean saveComment(Comment comment) {
        // here we can add filter login
        _commentRepository.save(comment);
        return true;
    }

    @Override
    public List<Comment> getAllComment(Long clothid) {
        return _commentRepository.findAllByClothid(clothid);
    }

    @Override
    public void deleteComment(Long commentId) {
        _commentRepository.deleteById(commentId);
    }

    @Override
    public List<Cloth> filterCloth(Filter filter) {
        List<Cloth> filteredCloth = _clothRepository.findAll();
        if(filter.name != null && !filter.name.isEmpty()) {
            filteredCloth = filteredCloth.stream().filter(f -> f.getName().contains(filter.name)).toList();
        }
        if(filter.brandNames != null && filter.brandNames.size() != 0) {
            filteredCloth = filteredCloth.stream().filter(f -> filter.brandNames.contains(f.getBrand_name())).toList();
        }
        if(filter.sections != null && filter.sections.size() != 0) {
            filteredCloth = filteredCloth.stream().filter(f -> filter.sections.contains(f.getSection())).toList();

        }
        if(filter.gender != null) {
            filteredCloth = filteredCloth.stream().filter(f -> filter.gender.equals(f.getGender().name())).toList();
        }
        if(filter.colors != null && filter.colors.size() != 0) {
            filteredCloth = filteredCloth
                    .stream()
                    .filter(
                            f -> f.colors.stream()
                                    .filter(filter.colors::contains)
                                    .collect(Collectors.toSet()).size() != 0)
                    .toList();
        }
        if(filter.sizes != null && filter.sizes.size() != 0) {
            filteredCloth = filteredCloth
                    .stream()
                    .filter(
                            f-> f.sizes.stream()
                                    .filter(filter.sizes::contains)
                                    .collect(Collectors.toSet()).size() != 0)
                    .toList();

        }
        if (filter.minPrice != -1) {
            filteredCloth = filteredCloth
                    .stream()
                    .filter(
                            f->f.getPrice() >= filter.minPrice
                    ).toList();
        }
        if (filter.maxPrice != -1) {
            filteredCloth = filteredCloth
                    .stream()
                    .filter(
                            f->f.getPrice() <= filter.maxPrice
                    ).toList();
        }
        return filteredCloth;
    }

    @Override
    public List<String> getAllBrands() {
        return _clothRepository.getDistinctBrands();
    }

    @Override
    public List<String> getAllSections() {
        return _clothRepository.getDistinctSections();
    }

    @Override
    public List<String> getAllColors() {
        return _clothRepository.getDistinctColors();
    }

    @Override
    public List<String> getAllSizes() {
        return _clothRepository.getDistinctSizes();
    }
}
