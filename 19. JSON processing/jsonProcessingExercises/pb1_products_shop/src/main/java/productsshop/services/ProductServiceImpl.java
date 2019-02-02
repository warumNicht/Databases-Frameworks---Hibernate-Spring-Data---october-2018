package productsshop.services;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsshop.domain.dto.ProductPriceInRangeDto;
import productsshop.domain.dto.seedDtos.ProductsSeedDto;
import productsshop.domain.entities.Category;
import productsshop.domain.entities.Product;
import productsshop.domain.entities.User;
import productsshop.repositories.CategoryRepository;
import productsshop.repositories.ProductRepository;
import productsshop.repositories.UserRepository;
import productsshop.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper mapper;
    private ValidatorUtil validator;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository,
                              CategoryRepository categoryRepository, ModelMapper mapper, ValidatorUtil validator) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.configureMapper();
        this.validator = validator;
    }

    private void configureMapper() {
        Converter<User, String> converter = new AbstractConverter<User, String>() {
            @Override
            protected String convert(User user) {
                return String.format("%s %s",
                        user.getFirstName() == null ? "" : user.getFirstName(),
                        user.getLastName()).trim();
            }
        };
        this.mapper.addConverter(converter);
        PropertyMap<Product, ProductPriceInRangeDto> map = new PropertyMap<Product, ProductPriceInRangeDto>() {
            @Override
            protected void configure() {
                using(converter).map(source.getSeller()).setSeller(null);
            }
        };
        this.mapper.addMappings(map);

    }

    @Override
    public void seedProducts(ProductsSeedDto[] productsSeedDtos) {
        if(this.productRepository.count()!=0){
            return;
        }
        for (ProductsSeedDto productsSeedDto : productsSeedDtos) {
            if (!this.validator.isValid(productsSeedDto)) {
                this.validator.getViolations(productsSeedDto)
                        .forEach(v -> System.out.println(v.getMessage()));
                continue;
            }


            User seller = this.getRandomUser();
            User buyer = this.getRandomUser();
            if ( buyer.getId() % 2 == 0||seller.equals(buyer) ) {
                buyer = null;
            }
            Set<Category> randomCategories = this.getRandomCategorySet();

            Product product = this.mapper.map(productsSeedDto, Product.class);
            product.setSeller(seller);
            product.setBuyer(buyer);
            product.setCategories(randomCategories);

            this.productRepository.saveAndFlush(product);
//
//            for (Category category : randomCategories) {
//                category.getProducts().add(product);
//                this.categoryRepository.save(category);
//            }
        }
    }

    @Override
    public ProductPriceInRangeDto[] getProductsInRange(BigDecimal lower, BigDecimal upper) {
        List<Product> result = this.productRepository.getAllByPriceIsBetweenAndBuyerIsNullOrderByPrice(lower, upper);
        ProductPriceInRangeDto[] resultDto = new ProductPriceInRangeDto[result.size()];

        for (int i = 0; i < resultDto.length; i++) {
            Product product = result.get(i);
            resultDto[i] = this.mapper.map(product, ProductPriceInRangeDto.class);
        }
        return resultDto;
    }

    private void registerProductDto(ProductsSeedDto productsSeedDto) {
        Product product = this.mapper.map(productsSeedDto, Product.class);
        //  Set<ConstraintViolation<Product>> constraintViolations = this.validator.validate(product);

        User seller = this.getRandomUser();
        User buyer = this.getRandomUser();
        if (seller.equals(buyer) || buyer.getId() % 2 == 0) {
            buyer = null;
        }
        Set<Category> randomCategories = this.getRandomCategorySet();

        Product productMapped = this.mapper.map(productsSeedDto, Product.class);
        productMapped.setSeller(seller);
        productMapped.setBuyer(buyer);
        productMapped.setCategories(randomCategories);

        this.productRepository.saveAndFlush(productMapped);

        for (Category category : randomCategories) {
            category.getProducts().add(productMapped);
            this.categoryRepository.save(category);
        }




    }

    private Set<Category> getRandomCategorySet() {
        long categoryCount = this.categoryRepository.count();
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int setLength = random.nextInt((int) categoryCount) + 1;

        for (int i = 0; i < setLength; i++) {
            long randomCatId = random.nextInt((int) categoryCount) + 1;
            Category category = this.categoryRepository.findById(randomCatId).orElse(null);
            result.add(category);
        }
        return result;
    }


    private User getRandomUser() {
        long usersCount = this.userRepository.count();
        Random random = new Random();
        long randomUserId = random.nextInt((int) usersCount) + 1;
        User user = this.userRepository.findById(randomUserId).orElse(null);
        return user;
    }
}
