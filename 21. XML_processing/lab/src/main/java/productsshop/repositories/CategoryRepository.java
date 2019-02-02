package productsshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import productsshop.domain.entities.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    boolean existsCategoriesByName(String name);

    @Query(value = "SELECT c.name,COUNT(p) AS countOFProducts,AVG (p.price),SUM(p.price) " +
            "FROM Category c LEFT JOIN c.products AS p " +
            "GROUP BY c.id " +
            "ORDER BY countOFProducts DESC")
    List<Object[]> getAllByStatistics();
}
