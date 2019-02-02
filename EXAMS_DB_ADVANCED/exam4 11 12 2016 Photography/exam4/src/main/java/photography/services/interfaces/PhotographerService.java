package photography.services.interfaces;

import photography.domain.dto.PfotoStatDto;
import photography.domain.dto.PhotographerOrderedDto;
import photography.domain.dto.RootFotoDto;
import photography.domain.dto.importDto.photographers.PhotographerImportDto;
import photography.domain.dto.query3.RootFotoExportDto;

public interface PhotographerService {
    String importPhotographers(PhotographerImportDto[] photographerImportDtos);

    PhotographerOrderedDto[] query1();

    PfotoStatDto[] query2();

    RootFotoExportDto query3();
}
