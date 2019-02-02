package cars.services.interfaces;

import cars.domain.dto.LocalSupplierDto;
import cars.domain.dto.seedDtos.SupplierSeedDto;
import cars.domain.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void seedSuppliers(SupplierSeedDto[] supplierSeedDtos);

    List<LocalSupplierDto> getLocalSuppliers();
}
