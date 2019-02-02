package pb2_carsDealer.services;

import pb2_carsDealer.domain.dto.query6.AllSalesWithDiscountDto;
import pb2_carsDealer.domain.dto.query6.SaleDiscountDto;
import pb2_carsDealer.domain.entities.Car;
import pb2_carsDealer.domain.entities.Customer;
import pb2_carsDealer.domain.entities.Discount;
import pb2_carsDealer.domain.entities.Sale;
import pb2_carsDealer.repositories.CarRepository;
import pb2_carsDealer.repositories.CustomerRepository;
import pb2_carsDealer.repositories.SalesRepository;
import pb2_carsDealer.services.interfaces.SaleService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SaleService {
    private SalesRepository salesRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private ModelMapper mapper;
    private Random random;

    @Autowired
    public SalesServiceImpl(SalesRepository salesRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper mapper) {
        this.salesRepository = salesRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.random = new Random();
        this.configureMapper();
    }

    @Override
    public void seedSales(int salesNumber) {
        if(this.salesRepository.count()!=0){
            return;
        }
        for (int i = 0; i < salesNumber; i++) {
            Car car = this.getRandomCar();
            if(this.checkIfCarIsAreadySold(car)){
                System.out.println(String.format("Car id=%d %s %s is already sold!%nCannot be sold more!",
                        car.getId(),car.getMake(),car.getModel()));
                continue;
            }
            Customer customer = this.getRandomCustomer();
            int nextInt = this.random.nextInt(8);
            Discount randomDiscount = Discount.values()[nextInt];

            Sale sale=new Sale(randomDiscount,car,customer);
            this.salesRepository.saveAndFlush(sale);
        }
    }

    @Override
    public AllSalesWithDiscountDto getSalesWithDiscounts() {
        List<Sale> sales = this.salesRepository.findAll();
        AllSalesWithDiscountDto allSalesWithDiscountDto=new AllSalesWithDiscountDto();
        List<SaleDiscountDto> collect = sales.stream()
                .map(sale -> this.mapper.map(sale, SaleDiscountDto.class))
                .collect(Collectors.toList());
        allSalesWithDiscountDto.setSaleDiscountDtos(collect);
        return allSalesWithDiscountDto;
    }

    private boolean checkIfCarIsAreadySold(Car car) {
        if(this.salesRepository.existsByCar(car)){
            return true;
        }
        return false;
    }

    private Customer getRandomCustomer() {
        long customerCount = this.customerRepository.count();
        long nextInt = this.random.nextInt((int) customerCount) + 1;
        Customer customer = this.customerRepository.findById(nextInt).orElse(null);
        return customer;
    }

    private Car getRandomCar() {
        long carCount = this.carRepository.count();
        long nextInt = this.random.nextInt((int) carCount) + 1;
        Car car = this.carRepository.findById(nextInt).orElse(null);
        return car;
    }
    private void configureMapper() {
        Converter<Car,BigDecimal> priceConverter=new AbstractConverter<Car, BigDecimal>() {
            @Override
            protected BigDecimal convert(Car car) {
                return car.getTotalPrice();
            }
        };
        Converter<Sale,BigDecimal> discountPriceConverter=new AbstractConverter<Sale, BigDecimal>() {
            @Override
            protected BigDecimal convert(Sale sale) {
                BigDecimal discountPercent = sale.getDiscount().getDiscountPercent();
                if(sale.getCustomer().isYoungDriver()){
                    discountPercent=discountPercent.add(BigDecimal.valueOf(0.05));
                }
                BigDecimal coefficient=BigDecimal.valueOf(1).subtract(discountPercent);
                return sale.getCar().getTotalPrice().multiply(coefficient);
            }
        };
        PropertyMap<Sale,SaleDiscountDto> discountMap=new PropertyMap<Sale, SaleDiscountDto>() {
            @Override
            protected void configure() {
                using(priceConverter).map(source.getCar()).setPrice(BigDecimal.valueOf(0));
                using(discountPriceConverter).map(source).setPriceWithDiscount(BigDecimal.valueOf(0));
            }
        };
        this.mapper.addMappings(discountMap);
    }
}
