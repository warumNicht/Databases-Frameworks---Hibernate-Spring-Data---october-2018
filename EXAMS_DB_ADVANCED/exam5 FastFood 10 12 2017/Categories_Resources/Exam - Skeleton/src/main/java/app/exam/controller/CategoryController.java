package app.exam.controller;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.parser.XMLParser;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.util.List;

@Controller
public class CategoryController {
    private CategoryService categoryService;
    private XMLParser xmlParser;
@Autowired
    public CategoryController(CategoryService categoryService, XMLParser xmlParser) {
        this.categoryService = categoryService;
    this.xmlParser = xmlParser;
}

    public String getCategoriesWithMostPopularItemsSorted() throws JAXBException {
        CategoriesFrequentItemsXMLExportDTO categoriesWithMostPopularItems = this.categoryService
                .getCategoriesWithMostPopularItems(List.of("Chicken", "Toys", "Drinks"));
        String resToFile = this.xmlParser.write(categoriesWithMostPopularItems);
        return resToFile;
    }
}
