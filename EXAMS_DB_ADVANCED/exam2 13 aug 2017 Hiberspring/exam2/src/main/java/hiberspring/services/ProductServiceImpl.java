package hiberspring.services;

import hiberspring.domain.dto.importdto.products.ProductImportDto;
import hiberspring.domain.dto.importdto.products.ProductsRootImportDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.ProductRepository;
import hiberspring.services.interfaces.ProductService;
import hiberspring.util.TextUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private BranchRepository branchRepository;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository,
                              ModelMapper mapper, ValidationUtil validationUtil) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importProducts(ProductsRootImportDto productsRootImportDto) {
        StringBuilder res = new StringBuilder();

        for (ProductImportDto productImportDto : productsRootImportDto.getProductImportDtos()) {
            if(!this.validationUtil.isValid(productImportDto)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Branch branch = this.branchRepository.findByName(productImportDto.getBranch()).orElse(null);
            if(branch==null){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Product product = this.mapper.map(productImportDto, Product.class);
            product.setBranch(branch);
            branch.getProducts().add(product);
            this.productRepository.saveAndFlush(product);

            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM, product.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
