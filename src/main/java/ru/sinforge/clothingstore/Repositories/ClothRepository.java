package ru.sinforge.clothingstore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sinforge.clothingstore.Entities.Cloth;

import java.util.List;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
    boolean existsByName(String name);
    Cloth getClothById(Long id);
    @Query(value= "SELECT DISTINCT c.brand_name FROM Cloth c")
    List<String> getDistinctBrands();
    @Query(value= "SELECT DISTINCT c.section FROM Cloth c")
    List<String> getDistinctSections();
    @Query(value= "SELECT DISTINCT c.colors FROM Cloth c")
    List<String> getDistinctColors();
    @Query(value= "SELECT DISTINCT c.sizes FROM Cloth c")
    List<String> getDistinctSizes();
}
