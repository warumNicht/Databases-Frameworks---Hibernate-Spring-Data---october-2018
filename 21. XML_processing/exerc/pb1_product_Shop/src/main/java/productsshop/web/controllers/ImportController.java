package productsshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import productsshop.domain.dto.seedDtos.CategoryRootDto;
import productsshop.domain.dto.seedDtos.ProductRootDto;
import productsshop.domain.dto.seedDtos.UsersRootDto;
import productsshop.services.CategoryService;
import productsshop.services.ProductService;
import productsshop.services.UserService;
import productsshop.util.XmlParser;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

@Controller
public class ImportController {
    private final static String USERS_FILE_PATH = "src\\main\\resources\\seedData\\users.xml";
    private final static String CATEGORIES_FILE_PATH = "src\\main\\resources\\seedData\\categories.xml";
    private final static String PRODUCTS_FILE_PATH = "src\\main\\resources\\seedData\\products.xml";

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private XmlParser xmlParser;

    @Autowired
    public ImportController(UserService userService, CategoryService categoryService,
                            ProductService productService, XmlParser xmlParser) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.xmlParser = xmlParser;
    }

    public void importUsers() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        UsersRootDto usersRootDto = this.xmlParser.parseXml(UsersRootDto.class, USERS_FILE_PATH);
        this.userService.seedUsers(usersRootDto);
    }

    public void importCategories() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        CategoryRootDto categoryRootDto = this.xmlParser.parseXml(CategoryRootDto.class, CATEGORIES_FILE_PATH);
        this.categoryService.seedCategories(categoryRootDto);
    }

    public void importProducts() throws FileNotFoundException, UnsupportedEncodingException, JAXBException {
        ProductRootDto productRootDto = this.xmlParser.parseXml(ProductRootDto.class, PRODUCTS_FILE_PATH);
        this.productService.seedProducts(productRootDto);
    }

}
