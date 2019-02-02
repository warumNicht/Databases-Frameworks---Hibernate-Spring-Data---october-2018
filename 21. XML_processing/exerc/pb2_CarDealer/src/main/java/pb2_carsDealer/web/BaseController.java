package pb2_carsDealer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public  class BaseController implements CommandLineRunner {
    private final ImportController importController;
    private final ExportController exportController;
@Autowired
    public BaseController(ImportController importController, ExportController exportController) {
        this.importController = importController;
    this.exportController = exportController;
}

    @Override
    public void run(String... args) throws Exception {
//        this.importController.importSuppliers();
//        this.importController.importParts();
//        this.importController.importCars();
//        this.importController.importCustomers();
//        this.exportController.seedSales(50);
//
//
//        this.exportController.orderdCustomers();  //pb1
//        this.exportController.carsWithMakeToyota(); //pb2
//        this.exportController.localSuppliers(); //pb3
//        this.exportController.carsWithTheirListOfParts(); //pb4
//        this.exportController.totalSalesByCustomer(); //pb5
//        this.exportController.salesWithAppliedDiscount(); //pb6
    }

}
