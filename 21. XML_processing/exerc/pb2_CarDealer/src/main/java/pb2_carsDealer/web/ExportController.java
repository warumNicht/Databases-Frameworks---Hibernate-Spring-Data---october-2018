package pb2_carsDealer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pb2_carsDealer.domain.dto.query1.AllOrderedCustomersDto;
import pb2_carsDealer.domain.dto.query2.AllCarsToyotaDto;
import pb2_carsDealer.domain.dto.query3.AllLocalSuppliersDto;
import pb2_carsDealer.domain.dto.query4.AllCarsWithPartsDto;
import pb2_carsDealer.domain.dto.query5.AllCustomersStatisticsDto;
import pb2_carsDealer.domain.dto.query6.AllSalesWithDiscountDto;
import pb2_carsDealer.services.interfaces.CarService;
import pb2_carsDealer.services.interfaces.CustomerService;
import pb2_carsDealer.services.interfaces.SaleService;
import pb2_carsDealer.services.interfaces.SupplierService;
import pb2_carsDealer.util.XmlParser;

import javax.xml.bind.JAXBException;

@Controller
public class ExportController {
    private final static String PB1_FILE_PATH = "src\\main\\resources\\resultFiles\\ordered-customers.xml";
    private final static String PB2_FILE_PATH = "src\\main\\resources\\resultFiles\\toyota-cars.xml";
    private final static String PB3_FILE_PATH = "src\\main\\resources\\resultFiles\\local-suppliers.xml";
    private final static String PB4_FILE_PATH = "src\\main\\resources\\resultFiles\\cars-and-parts.xml";
    private final static String PB5_FILE_PATH = "src\\main\\resources\\resultFiles\\customers-total-sales.xml";
    private final static String PB6_FILE_PATH = "src\\main\\resources\\resultFiles\\sales-discounts.xml";

    private CarService carService;
    private CustomerService customerService;
    private SupplierService supplierService;
    private SaleService saleService;
    private XmlParser xmlParser;

    @Autowired
    public ExportController(CarService carService, CustomerService customerService, SupplierService supplierService, SaleService saleService, XmlParser xmlParser) {
        this.carService = carService;
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.saleService = saleService;
        this.xmlParser = xmlParser;
    }

    public void orderdCustomers() throws JAXBException {
        AllOrderedCustomersDto orderdCustomers = this.customerService.getOrderdCustomers();
        this.xmlParser.exportToXml(orderdCustomers, PB1_FILE_PATH);
    }

    public void carsWithMakeToyota() throws JAXBException {
        AllCarsToyotaDto allToyota = this.carService.getAllToyota();
        this.xmlParser.exportToXml(allToyota, PB2_FILE_PATH);
    }

    public void localSuppliers() throws JAXBException {
        AllLocalSuppliersDto localSuppliers = this.supplierService.getLocalSuppliers();
        this.xmlParser.exportToXml(localSuppliers,PB3_FILE_PATH);
    }

    public void carsWithTheirListOfParts() throws JAXBException {
        AllCarsWithPartsDto carsWithParts = this.carService.getCarsWithParts();
        this.xmlParser.exportToXml(carsWithParts, PB4_FILE_PATH);
    }

    public void totalSalesByCustomer() throws JAXBException {
        AllCustomersStatisticsDto customerStatistics = this.customerService.getCustomerStatistics();
        this.xmlParser.exportToXml(customerStatistics,PB5_FILE_PATH);
    }

    public void salesWithAppliedDiscount() throws JAXBException {
        AllSalesWithDiscountDto salesWithDiscounts = this.saleService.getSalesWithDiscounts();
        this.xmlParser.exportToXml(salesWithDiscounts,PB6_FILE_PATH);
    }

    public void seedSales(int count){
        this.saleService.seedSales(count);
    }


}
