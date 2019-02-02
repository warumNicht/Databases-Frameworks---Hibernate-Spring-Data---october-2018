package productsshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import productsshop.domain.entities.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> getAllByPriceIsBetweenAndBuyerIsNullOrderByPrice( BigDecimal lowerRange, BigDecimal upperRange);
}
