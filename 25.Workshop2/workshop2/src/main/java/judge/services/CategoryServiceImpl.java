package judge.services;

import com.google.gson.Gson;
import judge.domain.dtos.CategoryImportDto;
import judge.domain.entities.Category;
import judge.repositories.CategoryRepository;
import judge.services.inretfaces.CategoryService;
import judge.util.FileUtil;
import judge.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public String importCategories(String categoriesFilePath) throws IOException {
        String content = this.fileUtil.readFile(categoriesFilePath);
        CategoryImportDto[] categoryImportDtos = this.gson.fromJson(content, CategoryImportDto[].class);

        for (CategoryImportDto categoryImportDto : categoryImportDtos) {
            Category category = this.mapper.map(categoryImportDto, Category.class);
            this.setParentCategory(category.getSubcategories(),category);
        }
        return null;
    }

    private void setParentCategory(Set<Category> subcategories, Category parent) {
        parent.setSubcategories(new HashSet<>());
        this.categoryRepository.saveAndFlush(parent);
        if(subcategories==null){
            return;
        }
        for (Category subcategory : subcategories) {
            this.setParentCategory(subcategory.getSubcategories(),subcategory);
            parent.getSubcategories().add(subcategory);
        }
    }
}
