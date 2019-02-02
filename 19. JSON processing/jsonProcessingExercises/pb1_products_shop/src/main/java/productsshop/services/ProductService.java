package productsshop.services;

import productsshop.domain.dto.ProductPriceInRangeDto;
import productsshop.domain.dto.seedDtos.ProductsSeedDto;

import java.math.BigDecimal;

public interface ProductService {

    void seedProducts(ProductsSeedDto[] productsSeedDtos);

    ProductPriceInRangeDto[] getProductsInRange(BigDecimal lower, BigDecimal upper);
}
