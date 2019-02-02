package pb2_carsDealer.services.interfaces;

import pb2_carsDealer.domain.dto.query3.AllLocalSuppliersDto;
import pb2_carsDealer.domain.dto.query3.LocalSupplierDto;
import pb2_carsDealer.domain.dto.seedDtos.SupplierRootDto;

import java.util.List;

public interface SupplierService {
    void seedSuppliers(SupplierRootDto supplierRootDto);

    AllLocalSuppliersDto getLocalSuppliers();
}
