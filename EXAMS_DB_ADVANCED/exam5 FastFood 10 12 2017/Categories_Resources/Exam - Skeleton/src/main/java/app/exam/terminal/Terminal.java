package app.exam.terminal;

import app.exam.controller.CategoryController;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Component
public class Terminal implements CommandLineRunner {
    private final static String EMPLOYEES_FILE_PATH = "src\\main\\resources\\files\\json\\employees.json";
    private final static String ITEMS_FILE_PATH = "src\\main\\resources\\files\\json\\items.json";
    private final static String ORDERS_FILE_PATH = "src\\main\\resources\\files\\xml\\orders.xml";

    private final static String PB1_FILE_PATH = "src\\main\\resources\\output\\query1.json";
    private final static String PB2_FILE_PATH = "src\\main\\resources\\output\\query2.xml";

    private EmployeesController employeesController;
    private CategoryController categoryController;
    private ItemsController itemsController;
    private OrdersController ordersController;
    private FileIO fileIO;
@Autowired
    public Terminal(EmployeesController employeesController, CategoryController categoryController,
                    ItemsController itemsController, OrdersController ordersController, FileIO fileIO) {
        this.employeesController = employeesController;
        this.categoryController = categoryController;
        this.itemsController = itemsController;
        this.ordersController = ordersController;
    this.fileIO = fileIO;
}

    @Override
    public void run(String... args) throws Exception {
//        this.importEmployees();
//        importItems();
//        importOrders();
        query2();
    }
    private void importEmployees() throws IOException, JAXBException {
        String content = this.fileIO.read(EMPLOYEES_FILE_PATH);
        String res = this.employeesController.importDataFromJSON(content);
        System.out.println(res);
    }

    private void importItems() throws IOException, JAXBException {
        String content = this.fileIO.read(ITEMS_FILE_PATH);
        String res = this.itemsController.importDataFromJSON(content);
        System.out.println(res);
    }

    private void importOrders() throws IOException, JAXBException, ParseException {
        String content = this.fileIO.read(ORDERS_FILE_PATH);
        String res = this.ordersController.importDataFromXML(content);
        System.out.println(res);
    }
    private void query1() throws IOException, JAXBException {
        String res = this.ordersController.exportOrdersByEmployeeAndOrderType("Avery Rush", "ToGo");
        this.fileIO.write(res,PB1_FILE_PATH);
    }

    private void query2() throws IOException, JAXBException {
        String fileContent = this.categoryController.getCategoriesWithMostPopularItemsSorted();
        this.fileIO.write(fileContent,PB2_FILE_PATH);
    }
}
