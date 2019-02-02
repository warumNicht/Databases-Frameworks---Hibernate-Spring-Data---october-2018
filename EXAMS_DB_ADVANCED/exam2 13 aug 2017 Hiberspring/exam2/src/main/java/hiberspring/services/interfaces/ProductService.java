package hiberspring.services.interfaces;

import hiberspring.domain.dto.importdto.products.ProductsRootImportDto;

public interface ProductService {
    String importProducts(ProductsRootImportDto productsRootImportDto);
}
