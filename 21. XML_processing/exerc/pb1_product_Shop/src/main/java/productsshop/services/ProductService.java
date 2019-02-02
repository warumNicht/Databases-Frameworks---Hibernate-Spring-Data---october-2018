package productsshop.services;

import productsshop.domain.dto.query1.ProductPriceInRangeDto;
import productsshop.domain.dto.seedDtos.ProductRootDto;

import java.math.BigDecimal;

public interface ProductService {

    void seedProducts(ProductRootDto productsSeedDtos);

    ProductPriceInRangeDto[] getProductsInRange(BigDecimal lower, BigDecimal upper);
}
