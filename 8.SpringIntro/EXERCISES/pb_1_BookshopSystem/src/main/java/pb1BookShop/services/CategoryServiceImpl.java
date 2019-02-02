package pb1BookShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pb1BookShop.models.Category;
import pb1BookShop.repositories.CategoryRepository;
import pb1BookShop.util.FileUtil;

import java.io.IOException;

@Service
@Primary
public class CategoryServiceImpl implements CategoryService {
    private final String CATEGORIES_PATH="src\\main\\resources\\seedData\\categories.txt";
    private final FileUtil fileUtil;
    private CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories() throws IOException {
        if(this.categoryRepository.count()==0){
            String[] fileContent = this.fileUtil.getFileContent(CATEGORIES_PATH);
            for (String currentCategorie : fileContent) {
                if(!currentCategorie.equals("")){
                    Category category=new Category(currentCategorie);
                    this.register(category);
                }
            }
        }
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
