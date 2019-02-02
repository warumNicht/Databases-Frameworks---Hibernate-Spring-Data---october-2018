package pb2_carsDealer.services.interfaces;

import pb2_carsDealer.domain.dto.seedDtos.PartRootDto;
import pb2_carsDealer.domain.dto.seedDtos.PartSeedDto;

public interface PartService {
    void seedParts(PartRootDto partSeedDtos);
}
