package cars.web;

import cars.domain.dto.*;
import cars.domain.dto.query4.CarWithPartsDto;
import cars.domain.dto.seedDtos.CarSeedDto;
import cars.domain.dto.seedDtos.CustomerSeedDto;
import cars.domain.dto.seedDtos.PartSeedDto;
import cars.domain.dto.seedDtos.SupplierSeedDto;
import cars.io.FileIOUtil;
import cars.services.interfaces.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class CarDealerController implements CommandLineRunner {
    private final static String SUPPLIERS_FILE_PATH = "src\\main\\resources\\sourceJsons\\suppliers.json";
    private final static String CARS_FILE_PATH = "src\\main\\resources\\sourceJsons\\cars.json";
    private final static String CUSTOMERS_FILE_PATH = "src\\main\\resources\\sourceJsons\\customers.json";
    private final static String PARTS_FILE_PATH = "src\\main\\resources\\sourceJsons\\parts.json";

    private final static String PB1_FILE_PATH = "src\\main\\resources\\resultFiles\\ordered-customers.json";
    private final static String PB2_FILE_PATH = "src\\main\\resources\\resultFiles\\toyota-cars.json";
    private final static String PB3_FILE_PATH = "src\\main\\resources\\resultFiles\\local-suppliers.json";
    private final static String PB4_FILE_PATH = "src\\main\\resources\\resultFiles\\cars-and-parts.json";
    private final static String PB5_FILE_PATH = "src\\main\\resources\\resultFiles\\customers-total-sales.json";
    private final static String PB6_FILE_PATH = "src\\main\\resources\\resultFiles\\sales-discounts.json";

    private Gson gson;
    private SupplierService supplierService;
    private PartService partService;
    private CarService carService;
    private CustomerService customerService;
    private SaleService saleService;
    private FileIOUtil fileIOUtil;

    @Autowired
    public CarDealerController(Gson gson, SupplierService supplierService,
                               PartService partService, CarService carService,
                               CustomerService customerService, SaleService saleService,
                               FileIOUtil fileIOUtil) {
        this.gson = gson;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {


        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
//        this.seedSales(50);

//        this.orderedCustomers(); //pb1
//        this.carsFromMakeToyota(); //pb2
//        this.localSuppliers(); //pb3
//
//        this.carsWithTheirListOfParts(); //pb4
//        this.totalSalesByCustomer(); //pb5
//        this.salesWithAppliedDiscount(); //pb6

    }
    private void salesWithAppliedDiscount() throws IOException {
        List<SaleDiscountDto> salesWithDiscounts = this.saleService.getSalesWithDiscounts();
        String result = this.gson.toJson(salesWithDiscounts);
        this.fileIOUtil.write(PB6_FILE_PATH,result);
    }
    private void totalSalesByCustomer() throws IOException {
        List<CustomerStatisticsDto> customerStatistics = this.customerService.getCustomerStatistics();
        String jsonResult = this.gson.toJson(customerStatistics);
        this.fileIOUtil.write(PB5_FILE_PATH,jsonResult);
    }
    private void carsWithTheirListOfParts() throws IOException {
        List<CarWithPartsDto> carsWithParts = this.carService.getCarsWithParts();
        String jsonResult = this.gson.toJson(carsWithParts);
        this.fileIOUtil.write(PB4_FILE_PATH,jsonResult);
    }

    private void orderedCustomers() throws IOException {
        List<OrderedCustomerDto> orderdCustomers = this.customerService.getOrderdCustomers();
        String jsonResult = this.gson.toJson(orderdCustomers);
        this.fileIOUtil.write(PB1_FILE_PATH,jsonResult);
    }

    private void localSuppliers() throws IOException {
        List<LocalSupplierDto> localSuppliers = this.supplierService.getLocalSuppliers();
        String jsonResult = this.gson.toJson(localSuppliers);
        this.fileIOUtil.write(PB3_FILE_PATH,jsonResult);
    }

    private void carsFromMakeToyota() throws IOException {
        List<CarModelDto> allToyota = this.carService.getAllToyota();
        String jsonResult = this.gson.toJson(allToyota);
        this.fileIOUtil.write(PB2_FILE_PATH,jsonResult);
    }

    private void seedSuppliers() throws IOException {
        String suppliersFileContent = this.fileIOUtil.read(SUPPLIERS_FILE_PATH);
        SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(suppliersFileContent, SupplierSeedDto[].class);
        this.supplierService.seedSuppliers(supplierSeedDtos);
    }

    private void seedParts() throws IOException {
        String partFileContent = this.fileIOUtil.read(PARTS_FILE_PATH);
        PartSeedDto[] partSeedDtos = this.gson.fromJson(partFileContent, PartSeedDto[].class);
        this.partService.seedParts(partSeedDtos);
    }

    private void seedCars() throws IOException {
        String carsFileContent = this.fileIOUtil.read(CARS_FILE_PATH);
        CarSeedDto[] carSeedDtos = this.gson.fromJson(carsFileContent, CarSeedDto[].class);
        this.carService.seedCars(carSeedDtos);
    }

    private void seedCustomers() throws IOException {
        String customersFileContent = this.fileIOUtil.read(CUSTOMERS_FILE_PATH);
        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(customersFileContent, CustomerSeedDto[].class);
        this.customerService.seedCustomers(customerSeedDtos);
    }

    private void seedSales(int count){
        this.saleService.seedSales(count);
    }
}
