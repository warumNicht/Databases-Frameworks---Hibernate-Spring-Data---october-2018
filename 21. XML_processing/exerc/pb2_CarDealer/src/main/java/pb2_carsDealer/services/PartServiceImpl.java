package pb2_carsDealer.services;

import pb2_carsDealer.domain.dto.seedDtos.PartRootDto;
import pb2_carsDealer.domain.dto.seedDtos.PartSeedDto;
import pb2_carsDealer.domain.entities.Part;
import pb2_carsDealer.domain.entities.Supplier;
import pb2_carsDealer.repositories.PartsRepository;
import pb2_carsDealer.repositories.SupplierRepository;
import pb2_carsDealer.services.interfaces.PartService;
import pb2_carsDealer.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private PartsRepository partsRepository;
    private SupplierRepository supplierRepository;
    private ValidatorUtil validatorUtil;
    private ModelMapper mapper;

    @Autowired
    public PartServiceImpl(PartsRepository partsRepository, SupplierRepository supplierRepository, ValidatorUtil validatorUtil, ModelMapper mapper) {
        this.partsRepository = partsRepository;
        this.supplierRepository = supplierRepository;
        this.validatorUtil = validatorUtil;
        this.mapper = mapper;
    }

    @Override
    public void seedParts(PartRootDto partSeedDtos) {
        if (this.partsRepository.count() != 0) {
            return;
        }
        for (PartSeedDto partSeedDto : partSeedDtos.getPartSeedDtos()) {
            if (!this.validatorUtil.isValid(partSeedDto)) {
                this.validatorUtil.getViolations(partSeedDto)
                        .forEach(v -> System.out.println(v.getMessage()));
                System.out.println();
                continue;
            }
            Part part = this.mapper.map(partSeedDto, Part.class);
            Supplier supplier = this.getRandomSupplier();
            part.setSupplier(supplier);
            this.partsRepository.saveAndFlush(part);
        }
    }

    private Supplier getRandomSupplier() {
        long supplierCount = this.supplierRepository.count();
        Random random = new Random();
        long randomId = random.nextInt((int) supplierCount) + 1;
        Supplier supplier = this.supplierRepository.findById(randomId).orElse(null);
        return supplier;
    }
}
