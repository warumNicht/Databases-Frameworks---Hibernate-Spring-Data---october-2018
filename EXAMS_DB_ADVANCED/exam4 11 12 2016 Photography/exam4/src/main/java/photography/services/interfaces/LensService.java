package photography.services.interfaces;

import photography.domain.dto.importDto.LensImportDto;

public interface LensService {
    String importLenses(LensImportDto[] lensImportDtos);
}
