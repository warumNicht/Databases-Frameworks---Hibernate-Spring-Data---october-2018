package cars.services;

import cars.domain.dto.CustomerStatisticsDto;
import cars.domain.dto.OrderedCustomerDto;
import cars.domain.dto.seedDtos.CustomerSeedDto;
import cars.domain.entities.Customer;
import cars.domain.entities.Sale;
import cars.repositories.CustomerRepository;
import cars.services.interfaces.CustomerService;
import cars.util.StatisticsComparator;
import cars.util.ValidatorUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private Gson gson;
    private ValidatorUtil validatorUtil;
    private ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ValidatorUtil validatorUtil, ModelMapper mapper) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.validatorUtil = validatorUtil;
        this.mapper = mapper;
    }

    @Override
    public void seedCustomers(CustomerSeedDto[] customerSeedDtos) {
        if(this.customerRepository.count()!=0){
            return;
        }
        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
            if (!this.validatorUtil.isValid(customerSeedDto)) {
                this.validatorUtil.getViolations(customerSeedDto)
                        .forEach(v -> System.out.println(v.getMessage()));
                System.out.println();
                continue;
            }
            Customer customer = this.mapper.map(customerSeedDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public List<OrderedCustomerDto> getOrderdCustomers() {
        List<Customer> customers = this.customerRepository.OrderByBirthDateAscIsYoungDriver();
        return customers.stream()
                .map(c -> this.mapper.map(c, OrderedCustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerStatisticsDto> getCustomerStatistics() {
        List<Customer> customersByStatistics = this.customerRepository.getCustomersByStatistics();

        List<CustomerStatisticsDto> result=new ArrayList<>();

        for (Customer customer : customersByStatistics) {
            BigDecimal totalDiscountPrice=new BigDecimal(0);
            for (Sale sale : customer.getSales()) {
                BigDecimal totalInitialPrice = sale.getCar().getTotalPrice();
                BigDecimal discountPercent = sale.getDiscount().getDiscountPercent();
                if(customer.isYoungDriver()){
                    discountPercent=discountPercent.add(BigDecimal.valueOf(0.05));
                }
                BigDecimal finalCoefficient=BigDecimal.valueOf(1).subtract(discountPercent);
                BigDecimal finalPrice=totalInitialPrice.multiply(finalCoefficient);
                totalDiscountPrice=totalDiscountPrice.add(finalPrice);
            }
            CustomerStatisticsDto statisticsDto=new CustomerStatisticsDto(customer.getName(),
                    customer.getSales().size(),totalDiscountPrice);

            result.add(statisticsDto);
        }
        result.sort(new StatisticsComparator());

        return result;
    }
}
