package pb2_carsDealer.services.interfaces;

import pb2_carsDealer.domain.dto.query6.AllSalesWithDiscountDto;
import pb2_carsDealer.domain.dto.query6.SaleDiscountDto;

import java.util.List;

public interface SaleService {
    void seedSales(int salesNumber);

    AllSalesWithDiscountDto getSalesWithDiscounts();
}
