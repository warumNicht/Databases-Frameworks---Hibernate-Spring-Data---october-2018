package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        // TODO : Implement me

        List<Category> categories = this.categoryRepository.categoriesByCountOfItems();
        StringBuilder res=new StringBuilder();
        for (Category category : categories) {
            res.append(String.format("Category: %s",category.getName()))
                    .append(System.lineSeparator());
            for (Item item : category.getItems()) {
                res.append(String.format("---ItemName: %s",item.getName()))
                        .append(System.lineSeparator());
                String formattedPrice = String.format("%.2f", item.getPrice());
                res.append(String.format("---ItemPrice: %s",formattedPrice))
                        .append(System.lineSeparator());
                res.append(System.lineSeparator());
            }
        }
        return res.toString().trim();
    }
}
