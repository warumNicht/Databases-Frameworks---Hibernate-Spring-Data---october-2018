package cars.services.interfaces;

import cars.domain.dto.CustomerStatisticsDto;
import cars.domain.dto.OrderedCustomerDto;
import cars.domain.dto.seedDtos.CustomerSeedDto;

import java.util.List;

public interface CustomerService {
    void seedCustomers(CustomerSeedDto[] customerSeedDtos);

    List<OrderedCustomerDto> getOrderdCustomers();

    List<CustomerStatisticsDto> getCustomerStatistics();
}
