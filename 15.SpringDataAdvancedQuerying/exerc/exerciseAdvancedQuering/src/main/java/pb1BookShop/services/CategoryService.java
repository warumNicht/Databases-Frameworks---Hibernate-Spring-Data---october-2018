package pb1BookShop.services;

import pb1BookShop.models.Category;

public interface CategoryService {
    void register(Category category);
    int countAll();
    Category getCategoryById(int id);
}
