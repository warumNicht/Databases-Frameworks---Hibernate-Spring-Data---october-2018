package cars.services.interfaces;

import cars.domain.dto.SaleDiscountDto;

import java.util.List;

public interface SaleService {
    void seedSales(int salesNumber);

    List<SaleDiscountDto> getSalesWithDiscounts();
}
