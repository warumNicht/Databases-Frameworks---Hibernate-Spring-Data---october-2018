package hiberspring.web;

import com.google.gson.Gson;
import hiberspring.domain.dto.ProductivEmployeeDto;
import hiberspring.domain.dto.query3.TownStatisticsRootDto;
import hiberspring.domain.dto.importdto.BranchImportDto;
import hiberspring.domain.dto.importdto.EmployeeCardImportDto;
import hiberspring.domain.dto.importdto.TownImportDto;
import hiberspring.domain.dto.importdto.employeesImport.EmployeeRootImportDto;
import hiberspring.domain.dto.importdto.products.ProductsRootImportDto;
import hiberspring.domain.dto.query4.BranchStatRootDto;
import hiberspring.services.interfaces.*;
import hiberspring.util.FileUtil;
import hiberspring.util.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class AppController implements CommandLineRunner {
    private final static String TOWN_FILE_PATH = "src\\main\\resources\\files\\json\\input\\towns.json";
    private final static String BRANCHES_FILE_PATH = "src\\main\\resources\\files\\json\\input\\branches.json";
    private final static String EMPLOYEE_CARD_PATH = "src\\main\\resources\\files\\json\\input\\employee_cards.json";

    private final static String EMPLOYEES_FILE_PATH = "src\\main\\resources\\files\\xml\\input\\employees.xml";
    private final static String PRODUCTS_FILE_PATH = "src\\main\\resources\\files\\xml\\input\\products.xml";

    private final static String PB1_FILE_PATH = "src\\main\\resources\\files\\json\\output\\free_cards.json";
    private final static String PB2_FILE_PATH = "src\\main\\resources\\files\\json\\output\\productive-employees.json";
    private final static String PB3_FILE_PATH = "src\\main\\resources\\files\\xml\\output\\towns.xml";
    private final static String PB4_FILE_PATH = "src\\main\\resources\\files\\xml\\output\\top-branches.xml";

    private TownService townService;
    private BranchService branchService;
    private CardService cardService;
    private ProductService productService;
    private EmployeeService employeeService;
    private FileUtil fileUtil;
    private Gson gson;
    private XmlParser xmlParser;

    @Autowired
    public AppController(TownService townService, BranchService branchService, CardService cardService, ProductService productService, EmployeeService employeeService, FileUtil fileUtil, Gson gson, XmlParser xmlParser) {
        this.townService = townService;
        this.branchService = branchService;
        this.cardService = cardService;
        this.productService = productService;
        this.employeeService = employeeService;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.importTowns();
//        this.importBranches();
//        this.importCards();
//        this.importProducts();
//        this.importEmployees();
//        this.query1();
//        this.query2();
//        this.query3();
        this.query4();
    }

    private void importTowns() throws IOException {
        String contentFile = this.fileUtil.read(TOWN_FILE_PATH);
        TownImportDto[] townImportDtos = this.gson.fromJson(contentFile, TownImportDto[].class);
        String res = this.townService.importTowns(townImportDtos);
        System.out.println(res);
    }

    private void importBranches() throws IOException {
        String fileContent = this.fileUtil.read(BRANCHES_FILE_PATH);
        BranchImportDto[] branchImportDtos = this.gson.fromJson(fileContent, BranchImportDto[].class);
        String res = this.branchService.importBranches(branchImportDtos);
        System.out.println(res);
    }

    private void importCards() throws IOException {
        String content = this.fileUtil.read(EMPLOYEE_CARD_PATH);
        EmployeeCardImportDto[] employeeCardImportDtos = this.gson.fromJson(content, EmployeeCardImportDto[].class);
        String res = this.cardService.importCards(employeeCardImportDtos);
        System.out.println(res);
    }

    private void importProducts() throws JAXBException, FileNotFoundException {
        ProductsRootImportDto productsRootImportDto = this.xmlParser.read(ProductsRootImportDto.class, PRODUCTS_FILE_PATH);
        String res = this.productService.importProducts(productsRootImportDto);
        System.out.println(res);
    }

    private void importEmployees() throws JAXBException, FileNotFoundException {
        EmployeeRootImportDto employeeRootImportDto = this.xmlParser.read(EmployeeRootImportDto.class, EMPLOYEES_FILE_PATH);
        String res = this.employeeService.importEmployees(employeeRootImportDto);
        System.out.println(res);
    }

    private  void query1() throws IOException {
        EmployeeCardImportDto[] cardsWithoutUser = this.cardService.findCardsWithoutUser();
        String resultJson = this.gson.toJson(cardsWithoutUser);
        this.fileUtil.write(resultJson,PB1_FILE_PATH);
    }

    private  void query2() throws IOException {
        ProductivEmployeeDto[] productivEmployeeDtos = this.employeeService.productiveEmployees();
        String resultJson = this.gson.toJson(productivEmployeeDtos);
        this.fileUtil.write(resultJson,PB2_FILE_PATH);
    }
    private  void query3() throws IOException, JAXBException {
        TownStatisticsRootDto townStatistics = this.townService.getTownStatistics();
        this.xmlParser.write(townStatistics,PB3_FILE_PATH);
    }
    private  void query4() throws IOException, JAXBException {
        BranchStatRootDto branchStatistics = this.branchService.getBranchStatistics();
        this.xmlParser.write(branchStatistics,PB4_FILE_PATH);
    }
}
