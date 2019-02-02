package pb2_carsDealer.services;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import pb2_carsDealer.domain.dto.query5.AllCustomersStatisticsDto;
import pb2_carsDealer.domain.dto.query5.CustomerStatisticsDto;
import pb2_carsDealer.domain.dto.query1.AllOrderedCustomersDto;
import pb2_carsDealer.domain.dto.query1.OrderedCustomerDto;
import pb2_carsDealer.domain.dto.seedDtos.CustomerRootDto;
import pb2_carsDealer.domain.dto.seedDtos.CustomerSeedDto;
import pb2_carsDealer.domain.entities.Customer;
import pb2_carsDealer.domain.entities.Sale;
import pb2_carsDealer.repositories.CustomerRepository;
import pb2_carsDealer.services.interfaces.CustomerService;
import pb2_carsDealer.util.StatisticsComparator;
import pb2_carsDealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ValidatorUtil validatorUtil;
    private ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ValidatorUtil validatorUtil, ModelMapper mapper) {
        this.customerRepository = customerRepository;

        this.validatorUtil = validatorUtil;
        this.mapper = mapper;
//        Converter<BigDecimal,String> converter=
//                ctx->{
//                    return String.format("%.2f",ctx.getSource());
//                };
//        PropertyMap<Customer,CustomerStatisticsDto> map=new PropertyMap<Customer, CustomerStatisticsDto>() {
//            @Override
//            protected void configure() {
//                using(converter).map(Customer::);
//            }
//        };
    }

    @Override
    public void seedCustomers(CustomerRootDto customerSeedDtos) {
        if(this.customerRepository.count()!=0){
            return;
        }
        for (CustomerSeedDto customerSeedDto : customerSeedDtos.getCustomerSeedDtos()) {
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
    public AllOrderedCustomersDto getOrderdCustomers() {
        List<Customer> customers = this.customerRepository.OrderByBirthDateAscIsYoungDriver();

        AllOrderedCustomersDto allOrderedCustomersDto=new AllOrderedCustomersDto();
        List<OrderedCustomerDto> collect = customers.stream()
                .map(c -> this.mapper.map(c, OrderedCustomerDto.class))
                .collect(Collectors.toList());
        allOrderedCustomersDto.setAllOrderedCustomersDtos(collect);
        return allOrderedCustomersDto;
    }

    @Override
    public AllCustomersStatisticsDto getCustomerStatistics() {
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
        AllCustomersStatisticsDto customersStatisticsDto=new AllCustomersStatisticsDto();
        customersStatisticsDto.setCustomerStatisticsDtos(result);
        return customersStatisticsDto;
    }
}
