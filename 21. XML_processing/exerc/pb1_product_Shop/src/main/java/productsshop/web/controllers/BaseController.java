package productsshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController implements CommandLineRunner {
    private ImportController importController;
    private ExportController exportController;
@Autowired
    public BaseController(ImportController importController, ExportController exportController) {
        this.importController = importController;
        this.exportController = exportController;
    }

    @Override
    public void run(String... args) throws Exception {
        this.importController.importUsers();
        this.importController.importCategories();
        this.importController.importProducts();

        this.exportController.productsInRange();  //pb1
        this.exportController.successfullySoldProducts();  //pb2
        this.exportController.categoriesByProductsCount();  //pb3
        this.exportController.usersAndProducts();  //pb4

    }
}
