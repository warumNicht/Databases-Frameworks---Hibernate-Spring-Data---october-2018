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
import productsshop.domain.dto.xml.ProductUserDto;
import productsshop.domain.dto.xml.ProductUserListDto;
import productsshop.io.FileIOUtil;
import productsshop.services.CategoryService;
import productsshop.services.ProductService;
import productsshop.services.UserService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductShopController implements CommandLineRunner {
    private final static String USERS_FILE_PATH = "src\\main\\resources\\seedData\\users.json";
    private final static String CATEGORIES_FILE_PATH = "src\\main\\resources\\seedData\\categories.json";
    private final static String PRODUCTS_FILE_PATH = "src\\main\\resources\\seedData\\products.json";

    private final static String PB1_PRODUCTS_IN_RANGE_RESULT_FILE_PATH = "src\\main\\resources\\resultingJsons\\products-in-range.json";
    private final static String PB2_SUCCESSFULLY_SOLD_PRODUCTS_FILE_PATH = "src\\main\\resources\\resultingJsons\\users-sold-products.json";
    private final static String PB3_CATEGORIES_BY_PRODUCTS_COUNT_FILE_PATH = "src\\main\\resources\\resultingJsons\\categories-by-products.json";
    private final static String PB4_USERS_AND_PRODUCTS_FILE_PATH = "src\\main\\resources\\resultingJsons\\users-and-products.json";

    private final static String XML_1_FILE_PATH = "src\\main\\resources\\xmlOutput\\problem1.xml";

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
//        this.seedUsers();
//        this.seedCategories();
//        this.seedProducts();
        List<ProductUserDto> productUserDtos = this.productService.productsWithUser();
        ProductUserListDto list=new ProductUserListDto();
        list.setProducts(productUserDtos);

        ProductPriceInRangeDto[] productsInRange = this.productService.
                getProductsInRange(BigDecimal.valueOf(600), BigDecimal.valueOf(700));
        ProductPriceInRangeDto product = productsInRange[0];

        ProductPriceInRangeListDto productsList=new ProductPriceInRangeListDto();
        productsList.setProducts(Arrays.asList(productsInRange));

        JAXBContext context=JAXBContext.newInstance(list.getClass());
        Marshaller marshaller=context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        StringWriter writer=new StringWriter();

        marshaller.marshal(list,writer);
        System.out.println(writer);

        String xml="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<products>\n" +
                "    <product employee_name=\"Ketorolac Tromethamine\">\n" +
                "        <discount-price>608.18</discount-price>\n" +
                "        <seller>Diana Harvey</seller>\n" +
                "    </product>\n" +
                "    <product employee_name=\"AMARANTHUS PALMERI POLLEN\">\n" +
                "        <discount-price>623.16</discount-price>\n" +
                "        <seller>Thompson</seller>\n" +
                "    </product>\n" +
                "    <product employee_name=\"AVANDAMET\">\n" +
                "        <discount-price>632.08</discount-price>\n" +
                "        <seller>Jacqueline Perez</seller>\n" +
                "    </product>\n" +
                "    <product employee_name=\"Imipramine Hydrochloride\">\n" +
                "        <discount-price>648.69</discount-price>\n" +
                "        <seller>Betty Lawson</seller>\n" +
                "    </product>\n" +
                "    <product employee_name=\"Childrens Allegra Allergy\">\n" +
                "        <discount-price>650.97</discount-price>\n" +
                "        <seller>Patricia Fuller</seller>\n" +
                "    </product>\n" +
                "    <product employee_name=\"Strattera\">\n" +
                "        <discount-price>658.54</discount-price>\n" +
                "        <seller>Moreno</seller>\n" +
                "    </product>\n" +
                "    <product employee_name=\"ENBREL\">\n" +
                "        <discount-price>673.97</discount-price>\n" +
                "        <seller>Eugene Stewart</seller>\n" +
                "    </product>\n" +
                "    <product employee_name=\"PRIMAXIN\">\n" +
                "        <discount-price>686.66</discount-price>\n" +
                "        <seller>Carl Lawson</seller>\n" +
                "    </product>\n" +
                "</products>";

        this.fileIOUtil.write(XML_1_FILE_PATH,xml);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader=new StringReader(xml);
//        ProductPriceInRangeListDto unmarshal = (ProductPriceInRangeListDto)unmarshaller.unmarshal(reader);
        System.out.println();

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
