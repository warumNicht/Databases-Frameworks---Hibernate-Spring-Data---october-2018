package app.repositories;

import app.model.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IngredientRepository extends CrudRepository<BasicIngredient,Long> {
    List<BasicIngredient> getAllByNameStartingWith(String prefix);

    List<BasicIngredient> getAllByNameInOrderByPrice(List<String> names);

    @Transactional
    @Modifying
    void deleteIngredientByName(String name);

    @Transactional
    @Modifying
    void updateIngredientsPrices();

    @Transactional
    @Modifying
    @Query(value = "UPDATE BasicIngredient AS i SET i.price=i.price*1.1 WHERE i.name IN :ingredientsToUpdate")
    void  updateIngredientsPricesInList(@Param(value = "ingredientsToUpdate") List<String> ingredientsToUpdate);

}
