package pb2_carsDealer.services;

import pb2_carsDealer.domain.dto.query3.AllLocalSuppliersDto;
import pb2_carsDealer.domain.dto.query3.LocalSupplierDto;
import pb2_carsDealer.domain.dto.seedDtos.SupplierRootDto;
import pb2_carsDealer.domain.dto.seedDtos.SupplierSeedDto;
import pb2_carsDealer.domain.entities.Part;
import pb2_carsDealer.domain.entities.Supplier;
import pb2_carsDealer.repositories.SupplierRepository;
import pb2_carsDealer.services.interfaces.SupplierService;
import pb2_carsDealer.util.ValidatorUtil;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private SupplierRepository supplierRepository;
    private ModelMapper mapper;
    private ValidatorUtil validatorUtil;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper mapper, ValidatorUtil validatorUtil) {
        this.supplierRepository = supplierRepository;
        this.mapper = mapper;
        this.validatorUtil = validatorUtil;
        this.configureMapper();
    }

    @Override
    public void seedSuppliers(SupplierRootDto supplierRootDto) {
        if(this.supplierRepository.count()!=0){
            return;
        }
        for (SupplierSeedDto supplierSeedDto : supplierRootDto.getSupplierSeedDtos()) {
            if(!this.validatorUtil.isValid(supplierSeedDto)){
                this.validatorUtil.getViolations(supplierSeedDto)
                        .forEach(v-> System.out.println(v.getMessage()));
                continue;
            }
            Supplier supplier = this.mapper.map(supplierSeedDto, Supplier.class);
            this.supplierRepository.saveAndFlush(supplier);
        }

    }

    @Override
    public AllLocalSuppliersDto getLocalSuppliers() {
        List<Supplier> notImportingParts = this.supplierRepository.getAllNotImportingParts();
        AllLocalSuppliersDto result=new AllLocalSuppliersDto();
        List<LocalSupplierDto> collect = notImportingParts.stream()
                .map(p -> this.mapper.map(p, LocalSupplierDto.class))
                .collect(Collectors.toList());
        result.setLocalSupplierDtos(collect);
        return result;
    }

    private void configureMapper() {
        Converter<Set<Part>,Integer> converter=new AbstractConverter<Set<Part>, Integer>() {
            @Override
            protected Integer convert(Set<Part> parts) {
                return parts.size();
            }
        };
        PropertyMap<Supplier,LocalSupplierDto> localSuppl=new PropertyMap<Supplier, LocalSupplierDto>() {
            @Override
            protected void configure() {
                using(converter).map(source.getParts()).setPartsCount(0);
            }
        };
        this.mapper.addMappings(localSuppl);
    }
}
