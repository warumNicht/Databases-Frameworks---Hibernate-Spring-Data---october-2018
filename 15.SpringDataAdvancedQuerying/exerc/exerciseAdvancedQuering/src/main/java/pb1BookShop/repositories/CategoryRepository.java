package pb1BookShop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb1BookShop.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Category c WHERE c.name = ?1")
    boolean existsCategoryByName(String name);
    int countAllByIdAfter(int id);

    Category getById(int id);
}
