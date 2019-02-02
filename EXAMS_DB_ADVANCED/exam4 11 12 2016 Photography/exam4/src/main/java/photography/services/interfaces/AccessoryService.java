package photography.services.interfaces;

import photography.domain.dto.importDto.accsessories.AccessoryRootImportDto;

public interface AccessoryService {
    String importAccessories(AccessoryRootImportDto accessoryRootImportDto);
}
