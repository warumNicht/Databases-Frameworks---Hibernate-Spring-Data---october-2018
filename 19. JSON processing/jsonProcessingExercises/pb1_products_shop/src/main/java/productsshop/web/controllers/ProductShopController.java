package productsshop.web.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import productsshop.domain.dto.*;
import productsshop.domain.dto.query4.CountAndUsersWithSoldDto;
import productsshop.domain.dto.seedDtos.CategorySeedDto;
import productsshop.domain.dto.seedDtos.ProductsSeedDto;
import productsshop.domain.dto.seedDtos.UserSeedDto;
import productsshop.io.FileIOUtil;
import productsshop.services.CategoryService;
import productsshop.services.ProductService;
import productsshop.services.UserService;
import java.io.IOException;
import java.math.BigDecimal;

@Controller
public class ProductShopController implements CommandLineRunner {
    private final static String USERS_FILE_PATH = "src\\main\\resources\\seedData\\users.json";
    private final static String CATEGORIES_FILE_PATH = "src\\main\\resources\\seedData\\categories.json";
    private final static String PRODUCTS_FILE_PATH = "src\\main\\resources\\seedData\\products.json";

    private final static String PB1_PRODUCTS_IN_RANGE_RESULT_FILE_PATH = "src\\main\\resources\\resultingJsons\\products-in-range.json";
    private final static String PB2_SUCCESSFULLY_SOLD_PRODUCTS_FILE_PATH = "src\\main\\resources\\resultingJsons\\users-sold-products.json";
    private final static String PB3_CATEGORIES_BY_PRODUCTS_COUNT_FILE_PATH = "src\\main\\resources\\resultingJsons\\categories-by-products.json";
    private final static String PB4_USERS_AND_PRODUCTS_FILE_PATH = "src\\main\\resources\\resultingJsons\\users-and-products.json";

    private final FileIOUtil fileIOUtil;
    private final Gson gson;
    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public ProductShopController(FileIOUtil fileIOUtil, Gson gson, UserService userService,
                                 CategoryService categoryService, ProductService productService) {
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedUsers();
        this.seedCategories();
        this.seedProducts();

//        this.productsInRange(); //pb1
//        this.successfullySoldProducts(); //pb2
//        this.categoriesByProductsCount();//pb3
//        this.usersAndProducts(); //pb4
    }

    private void seedUsers() throws IOException {
        String usersFileContent = this.fileIOUtil.read(USERS_FILE_PATH);
        UserSeedDto[] seedDtos = this.gson.fromJson(usersFileContent, UserSeedDto[].class);
        this.userService.seedUsers(seedDtos);
    }

    private void seedCategories() throws IOException {
        String categoriesFileContent = this.fileIOUtil.read(CATEGORIES_FILE_PATH);
        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(categoriesFileContent, CategorySeedDto[].class);
        this.categoryService.seedCategories(categorySeedDtos);
    }

    private void seedProducts() throws IOException {
        String productsFileContent = this.fileIOUtil.read(PRODUCTS_FILE_PATH);
        ProductsSeedDto[] productsSeedDtos = this.gson.fromJson(productsFileContent, ProductsSeedDto[].class);
        this.productService.seedProducts(productsSeedDtos);
    }

    private void productsInRange() throws IOException {
        ProductPriceInRangeDto[] productsInRange = this.productService
                .getProductsInRange(new BigDecimal(500), new BigDecimal(1000));
        String fileStringResult = this.gson.toJson(productsInRange);
        this.fileIOUtil.write(PB1_PRODUCTS_IN_RANGE_RESULT_FILE_PATH, fileStringResult);
    }

    private void successfullySoldProducts() throws IOException {
        UserSoldDto[] usersWithSoldProduct = this.userService.getUsersWithSoldProduct();
        String fileStringResult = this.gson.toJson(usersWithSoldProduct);
        this.fileIOUtil.write(PB2_SUCCESSFULLY_SOLD_PRODUCTS_FILE_PATH, fileStringResult);
    }

    private void categoriesByProductsCount() throws IOException {
        CategoryStatisticsDto[] statistics = this.categoryService.getCategoryStatistics();
        String fileStringResult = this.gson.toJson(statistics);
        this.fileIOUtil.write(PB3_CATEGORIES_BY_PRODUCTS_COUNT_FILE_PATH, fileStringResult);
    }

    private void usersAndProducts() throws IOException {
        CountAndUsersWithSoldDto countAndUsersWithSoldDto = this.userService.usersProducts();
        String fileContent = this.gson.toJson(countAndUsersWithSoldDto);
        this.fileIOUtil.write(PB4_USERS_AND_PRODUCTS_FILE_PATH, fileContent);
    }
}
