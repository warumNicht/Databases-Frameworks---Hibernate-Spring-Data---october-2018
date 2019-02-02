package pb1BookShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pb1BookShop.models.Category;
import pb1BookShop.repositories.CategoryRepository;

@Service
@Primary
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void register(Category category) {
        if(this.categoryRepository.existsCategoryByName(category.getName())){
            throw new IllegalArgumentException(String.format("Category  %s already exists",category.getName()));
        }
        this.categoryRepository.save(category);
    }

    @Override
    public int countAll() {
        return this.categoryRepository.countAllByIdAfter(0);
    }

    @Override
    public Category getCategoryById(int id) {
        return this.categoryRepository.getById(id);
    }
}
