package productsshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import productsshop.domain.dto.query1.ProductPriceInRangeDto;
import productsshop.domain.dto.query1.ProductsInRangeRootDto;
import productsshop.domain.dto.query2.UserSoldDto;
import productsshop.domain.dto.query2.UsersSoldRootDto;
import productsshop.domain.dto.query3.CategoryStatisticsRootDto;
import productsshop.domain.dto.query4.CountAndUsersWithSoldRootDto;
import productsshop.services.CategoryService;
import productsshop.services.ProductService;
import productsshop.services.UserService;
import productsshop.util.XmlParser;
import javax.xml.bind.JAXBException;
import java.math.BigDecimal;

@Controller
public class ExportController {
    private final static String PB1_PRODUCTS_IN_RANGE_RESULT_FILE_PATH = "src\\main\\resources\\resultFiles\\products-in-range.xml";
    private final static String PB2_SUCCESSFULLY_SOLD_PRODUCTS_FILE_PATH = "src\\main\\resources\\resultFiles\\users-sold-products.xml";
    private final static String PB3_CATEGORIES_BY_PRODUCTS_COUNT_FILE_PATH = "src\\main\\resources\\resultFiles\\categories-by-products.xml";
    private final static String PB4_USERS_AND_PRODUCTS_FILE_PATH = "src\\main\\resources\\resultFiles\\users-and-products.xml";

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private XmlParser xmlParser;
@Autowired
    public ExportController(UserService userService, CategoryService categoryService,
                            ProductService productService, XmlParser xmlParser) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.xmlParser = xmlParser;
    }

    public  void productsInRange() throws JAXBException {
        ProductPriceInRangeDto[] productsInRange = this.productService.
                getProductsInRange(new BigDecimal(500), new BigDecimal(1000));
        ProductsInRangeRootDto result=new ProductsInRangeRootDto();
        result.setProductSoldDtos(productsInRange);
        this.xmlParser.exportToXml(result,PB1_PRODUCTS_IN_RANGE_RESULT_FILE_PATH);
    }

    public void successfullySoldProducts() throws JAXBException {
        UserSoldDto[] usersWithSoldProduct = this.userService.getUsersWithSoldProduct();
        UsersSoldRootDto result=new UsersSoldRootDto();
        result.setUserSoldDtos(usersWithSoldProduct);
        this.xmlParser.exportToXml(result,PB2_SUCCESSFULLY_SOLD_PRODUCTS_FILE_PATH);
    }

    public void categoriesByProductsCount() throws JAXBException {
        CategoryStatisticsRootDto categoryStatistics = this.categoryService.getCategoryStatistics();
        this.xmlParser.exportToXml(categoryStatistics,PB3_CATEGORIES_BY_PRODUCTS_COUNT_FILE_PATH);
    }

    public void usersAndProducts() throws JAXBException {
        CountAndUsersWithSoldRootDto countAndUsersWithSoldRootDto = this.userService.usersProducts();
        this.xmlParser.exportToXml(countAndUsersWithSoldRootDto,PB4_USERS_AND_PRODUCTS_FILE_PATH);
    }



}
