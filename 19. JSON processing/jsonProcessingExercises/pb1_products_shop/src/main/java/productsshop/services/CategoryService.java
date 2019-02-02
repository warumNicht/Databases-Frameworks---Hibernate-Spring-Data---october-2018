package productsshop.services;

import productsshop.domain.dto.seedDtos.CategorySeedDto;
import productsshop.domain.dto.CategoryStatisticsDto;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    CategoryStatisticsDto[] getCategoryStatistics();
}
