package pb1BookShop.services;

import pb1BookShop.models.Category;

import java.io.IOException;

public interface CategoryService {
    void  seedCategories() throws IOException;

    void register(Category category);
    int countAll();
    Category getCategoryById(int id);
}
