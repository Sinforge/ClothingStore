package ru.sinforge.clothingstore.Services;

import org.springframework.web.multipart.MultipartFile;
import ru.sinforge.clothingstore.DTOs.Filter;
import ru.sinforge.clothingstore.Entities.Cloth;
import ru.sinforge.clothingstore.Entities.Comment;
import ru.sinforge.clothingstore.Entities.User;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ClothService {
    boolean addCloth(Cloth cloth, MultipartFile img) throws NoSuchAlgorithmException;

    Cloth getClothById(Long id);

    Iterable<Cloth> getAll();

    boolean saveComment(Comment comment);

    List<Comment> getAllComment(Long clothid);

    void deleteComment(Long commentId);


    List<Cloth> filterCloth(Filter filter);


    List<String> getAllBrands();
    List<String> getAllSections();
    List<String> getAllColors();
    List<String> getAllSizes();

    void deletePage(Long clothid);

    File getClothImage(String clothName);

    void saveChanges(Cloth cloth, MultipartFile img);

}
