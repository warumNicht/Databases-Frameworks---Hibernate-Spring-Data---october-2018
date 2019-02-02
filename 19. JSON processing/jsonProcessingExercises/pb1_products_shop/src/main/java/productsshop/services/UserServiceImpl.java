package productsshop.services;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productsshop.domain.dto.query4.AllSoldProductsDto;
import productsshop.domain.dto.query4.CountAndUsersWithSoldDto;
import productsshop.domain.dto.ProductSoldDto;
import productsshop.domain.dto.query4.SimpleSoldProductDto;
import productsshop.domain.dto.query4.UserWithSoldProducts;
import productsshop.domain.dto.seedDtos.UserSeedDto;
import productsshop.domain.dto.UserSoldDto;
import productsshop.domain.entities.Product;
import productsshop.domain.entities.User;
import productsshop.repositories.UserRepository;
import productsshop.util.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private UserRepository userRepository;
    private ValidatorUtil validator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.userRepository = userRepository;
        this.mapper = modelMapper;
        this.configureMapper();
        this.validator = validatorUtil;
    }

    private void configureMapper() {
        Converter<Set<Product>, List<ProductSoldDto>> productConverter = new AbstractConverter<Set<Product>, List<ProductSoldDto>>() {
            @Override
            protected List<ProductSoldDto> convert(Set<Product> products) {
                if (products.isEmpty()) {
                    return null;
                }
                List<ProductSoldDto> solded = new ArrayList<>();
                for (Product product : products) {
                    if (product.getBuyer() != null) {
                        ProductSoldDto productSoldDto = mapper.map(product, ProductSoldDto.class);
                        solded.add(productSoldDto);
                    }
                }
                return solded;
            }
        };

        PropertyMap<User, UserSoldDto> userWithSold = new PropertyMap<User, UserSoldDto>() {
            @Override
            protected void configure() {
                using(productConverter).map(source.getSoldProducts()).setSoldProducts(null);
            }
        };
        this.mapper.addMappings(userWithSold);

        Converter<Set<Product>,AllSoldProductsDto> productWithBuyerConverter=new AbstractConverter<Set<Product>, AllSoldProductsDto>() {
            @Override
            protected AllSoldProductsDto convert(Set<Product> products) {
                List<SimpleSoldProductDto> soldProducts=new ArrayList<>();
                for (Product product : products) {
                    if(product.getBuyer()!=null){
                        SimpleSoldProductDto simpleProduct = mapper.map(product, SimpleSoldProductDto.class);
                        soldProducts.add(simpleProduct);
                    }
                }
                AllSoldProductsDto result=new AllSoldProductsDto();
                result.setCount(soldProducts.size());
                result.setProducts(soldProducts);
                return result;
            }
        };
        PropertyMap<User,UserWithSoldProducts> userSoldProductsCount=new PropertyMap<User, UserWithSoldProducts>() {
            @Override
            protected void configure() {
                using(productWithBuyerConverter).map(source.getSoldProducts()).setSoldProducts(null);
            }
        };
       this.mapper.addMappings(userSoldProductsCount);
    }


    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
        if(this.userRepository.count()!=0){
            return;
        }
        for (UserSeedDto userSeedDto : userSeedDtos) {
            if (!this.validator.isValid(userSeedDto)) {
                this.validator.getViolations(userSeedDto)
                        .forEach(v -> System.out.println(v.getMessage()));
                continue;
            }
            User user = this.mapper.map(userSeedDto, User.class);
            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public UserSoldDto[] getUsersWithSoldProduct() {
        List<User> usersEntities = this.userRepository.getAllWithAtLeastOneSoldProduct();
        UserSoldDto[] result = new UserSoldDto[usersEntities.size()];
        for (int i = 0; i < result.length; i++) {
            UserSoldDto mappedUser = this.mapper.map(usersEntities.get(i), UserSoldDto.class);
            result[i] = mappedUser;
        }
        return result;
    }

    @Override
    public CountAndUsersWithSoldDto usersProducts() {
        List<User> allWithSoldProduct = this.userRepository.getAllWithSoldProduct();

        CountAndUsersWithSoldDto result=new CountAndUsersWithSoldDto();
        result.setUsersCount(allWithSoldProduct.size());

        List<UserWithSoldProducts> withSoldProducts = allWithSoldProduct.stream()
                .map(p -> this.mapper.map(p, UserWithSoldProducts.class))
                .collect(Collectors.toList());

        result.setUsers(withSoldProducts);
        return result;
    }

}
