package productsshop.services;

import productsshop.domain.dto.CategoryStatisticsDto;
import productsshop.domain.dto.seedDtos.CategorySeedDto;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    CategoryStatisticsDto[] getCategoryStatistics();
}
