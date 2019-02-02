package productsshop.services;

import productsshop.domain.dto.query3.CategoryStatisticsDto;
import productsshop.domain.dto.query3.CategoryStatisticsRootDto;
import productsshop.domain.dto.seedDtos.CategoryRootDto;

public interface CategoryService {

    void seedCategories(CategoryRootDto categorySeedDtos);

    CategoryStatisticsRootDto getCategoryStatistics();
}
