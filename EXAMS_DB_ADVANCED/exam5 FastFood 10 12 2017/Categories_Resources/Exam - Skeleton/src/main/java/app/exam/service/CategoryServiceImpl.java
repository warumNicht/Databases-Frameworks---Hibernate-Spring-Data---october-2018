package app.exam.service;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.repository.CategoryRepository;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoriesFrequentItemsXMLExportDTO getCategoriesWithMostPopularItems(List<String> categoryNames) {
        List<Object[]> objects = this.categoryRepository.catStatistics(categoryNames);
        List<Object[]> objects1 = this.categoryRepository.catStatisticsNative();
        List<Object[]> res = new ArrayList<>();

        List<String> collected = new ArrayList<>();
        for (Object[] object : objects) {

            String catName = (String) object[0];
            if (!collected.contains(catName)) {
                collected.add(catName);
                res.add(object);
            }
        }
        CategoriesFrequentItemsXMLExportDTO fin = new CategoriesFrequentItemsXMLExportDTO();
        List<CategoryExportDTO> categoryExportDTOS=new ArrayList<>();

        for (Object[] re : res) {
            CategoryExportDTO current=new CategoryExportDTO();
            String categoryName = (String) re[0];
            current.setName(categoryName);

            String productName = (String) re[1];
            BigDecimal totalSum = (BigDecimal) re[2];
            Long count = (Long) re[3];

            MostPopularItemDTO item=new MostPopularItemDTO();
            item.setName(productName);
            item.setTotalMade(totalSum);
            item.setSold(count);

            current.setMostPopularItemDTOS(item);
            categoryExportDTOS.add(current);
        }
        fin.setCategoryExportDTOS(categoryExportDTOS);
        return fin;
    }

    @Override
    public CategoriesFrequentItemsXMLExportDTO getCategoriesStat() {

        List<Object[]> objects = this.categoryRepository.catStatistics(List.of("Chicken", "Toys", "Drinks"));


        System.out.println();
        return null;
    }
}
