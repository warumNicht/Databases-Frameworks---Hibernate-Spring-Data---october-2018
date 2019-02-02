package app.repositories;

import app.model.enums.Size;
import app.model.ingredients.BasicIngredient;
import app.model.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface BasicShampooRepository extends CrudRepository<BasicShampoo,Long> {
    List<BasicShampoo> getAllBySizeOrderById(Size size);
    List<BasicShampoo> getAllBySizeOrLabel_IdOrderByPriceAsc(Size size,long labelId);

    List<BasicShampoo> getAllByPriceIsGreaterThanOrderByPriceDesc(BigDecimal price);

    int countByPriceIsLessThan(BigDecimal price);

    @Query(value = "SELECT s FROM BasicShampoo AS s JOIN s.ingredients AS i WHERE i IN :ingredients")
    Set<BasicShampoo> getAllByIngredientsIn(@Param(value = "ingredients") Set<BasicIngredient> ingredients);

    @Query(value = "SELECT s FROM BasicShampoo AS s WHERE s.ingredients.size< :size")
    List<BasicShampoo> getAllByIngredientsSize(@Param(value = "size") int size);

    double getSumOfIngredientsByBrand(String name);
}
