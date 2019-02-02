package cars.services.interfaces;

import cars.domain.dto.seedDtos.PartSeedDto;

public interface PartService {
    void seedParts(PartSeedDto[] partSeedDtos);
}
