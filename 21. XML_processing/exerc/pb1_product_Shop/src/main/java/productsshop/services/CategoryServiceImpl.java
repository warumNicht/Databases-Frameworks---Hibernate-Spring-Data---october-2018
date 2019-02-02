package productsshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsshop.domain.dto.query3.CategoryStatisticsDto;
import productsshop.domain.dto.query3.CategoryStatisticsRootDto;
import productsshop.domain.dto.seedDtos.CategoryRootDto;
import productsshop.domain.dto.seedDtos.CategorySeedDto;
import productsshop.domain.entities.Category;
import productsshop.repositories.CategoryRepository;
import productsshop.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;
    private ValidatorUtil validator;

@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper, ValidatorUtil validator) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public void seedCategories(CategoryRootDto categorySeedDtos) {
        if(this.categoryRepository.count()!=0){
            return;
        }
        for (CategorySeedDto categorySeedDto : categorySeedDtos.getCategorySeedDtos()) {
            if(!this.validator.isValid(categorySeedDto)){
                this.validator.getViolations(categorySeedDto)
                        .forEach(v-> System.out.println(v.getMessage()));
                continue;
            }
            Category category = this.mapper.map(categorySeedDto, Category.class);
            this.categoryRepository.saveAndFlush(category);
        }
    }

    @Override
    public CategoryStatisticsRootDto getCategoryStatistics() {
        List<Object[]> allByStatistics = this.categoryRepository.getAllByStatistics();
        CategoryStatisticsDto[] result=new CategoryStatisticsDto[allByStatistics.size()];

        for (int i = 0; i < result.length; i++) {
            Object[] tokens = allByStatistics.get(i);
            String name=(String)tokens[0];
            long count=(long)tokens[1];
            Double averagePrice=(Double) tokens[2];
            BigDecimal totalRevenue=(BigDecimal)tokens[3];

            CategoryStatisticsDto currentCat=new CategoryStatisticsDto(name,count,averagePrice,totalRevenue);
            result[i]=currentCat;
        }
        CategoryStatisticsRootDto categoryStatisticsRootDto=new CategoryStatisticsRootDto();
        categoryStatisticsRootDto.setCategoryStatisticsDtos(result);
        return categoryStatisticsRootDto;
    }

}
