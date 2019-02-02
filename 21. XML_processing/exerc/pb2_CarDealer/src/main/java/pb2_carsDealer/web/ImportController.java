package pb2_carsDealer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pb2_carsDealer.domain.dto.seedDtos.CarRootDto;
import pb2_carsDealer.domain.dto.seedDtos.CustomerRootDto;
import pb2_carsDealer.domain.dto.seedDtos.PartRootDto;
import pb2_carsDealer.domain.dto.seedDtos.SupplierRootDto;
import pb2_carsDealer.services.interfaces.CarService;
import pb2_carsDealer.services.interfaces.CustomerService;
import pb2_carsDealer.services.interfaces.PartService;
import pb2_carsDealer.services.interfaces.SupplierService;
import pb2_carsDealer.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

@Controller
public class ImportController {
    private final static String SUPPLIERS_FILE_PATH = "src\\main\\resources\\sourceXml\\suppliers.xml";
    private final static String PARTS_FILE_PATH = "src\\main\\resources\\sourceXml\\parts.xml";
    private final static String CARS_FILE_PATH = "src\\main\\resources\\sourceXml\\cars.xml";
    private final static String CUSTOMERS_FILE_PATH = "src\\main\\resources\\sourceXml\\customers.xml";
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final XmlParser xmlParser;

    @Autowired
    public ImportController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, XmlParser xmlParser) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.xmlParser = xmlParser;
    }

    public String importSuppliers() throws JAXBException, FileNotFoundException, UnsupportedEncodingException {

        SupplierRootDto supplierRootDto = this.xmlParser.parseXml(SupplierRootDto.class, SUPPLIERS_FILE_PATH);
        this.supplierService.seedSuppliers(supplierRootDto);
        return "Imported suppliers";
    }

    public String importParts() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        PartRootDto partRootDto = this.xmlParser.parseXml(PartRootDto.class, PARTS_FILE_PATH);
        this.partService.seedParts(partRootDto);
        return "Imported Parts";
    }

    public String importCars() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        CarRootDto carRootDto = this.xmlParser.parseXml(CarRootDto.class, CARS_FILE_PATH);
        this.carService.seedCars(carRootDto);
        return "Imported cars";
    }

    public  String importCustomers() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        CustomerRootDto customerRootDto = this.xmlParser.parseXml(CustomerRootDto.class, CUSTOMERS_FILE_PATH);
        this.customerService.seedCustomers(customerRootDto);
        return "Imported customers";
    }
}
