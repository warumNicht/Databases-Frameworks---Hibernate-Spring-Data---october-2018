package productsshop.services;

import productsshop.domain.dto.ProductPriceInRangeDto;
import productsshop.domain.dto.seedDtos.ProductsSeedDto;
import productsshop.domain.dto.xml.ProductUserDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts(ProductsSeedDto[] productsSeedDtos);

    ProductPriceInRangeDto[] getProductsInRange(BigDecimal lower, BigDecimal upper);

    List<ProductUserDto> productsWithUser();
}
