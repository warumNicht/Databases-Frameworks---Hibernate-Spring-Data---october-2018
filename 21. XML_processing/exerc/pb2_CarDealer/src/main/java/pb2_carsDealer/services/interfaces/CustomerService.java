package pb2_carsDealer.services.interfaces;

import pb2_carsDealer.domain.dto.query5.AllCustomersStatisticsDto;
import pb2_carsDealer.domain.dto.query5.CustomerStatisticsDto;
import pb2_carsDealer.domain.dto.query1.AllOrderedCustomersDto;
import pb2_carsDealer.domain.dto.seedDtos.CustomerRootDto;

import java.util.List;

public interface CustomerService {
    void seedCustomers(CustomerRootDto customerSeedDtos);

    AllOrderedCustomersDto getOrderdCustomers();

    AllCustomersStatisticsDto getCustomerStatistics();
}
