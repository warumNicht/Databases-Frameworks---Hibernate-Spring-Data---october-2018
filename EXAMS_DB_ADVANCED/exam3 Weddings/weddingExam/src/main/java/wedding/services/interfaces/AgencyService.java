package wedding.services.interfaces;

import wedding.domain.dto.importDto.AgencyExportDto;
import wedding.domain.dto.query4.TownAllRootDto;

public interface AgencyService {
    String importAgencies(AgencyExportDto[] agencyImportDtos);

    AgencyExportDto[] query1();

    TownAllRootDto query4();
}
