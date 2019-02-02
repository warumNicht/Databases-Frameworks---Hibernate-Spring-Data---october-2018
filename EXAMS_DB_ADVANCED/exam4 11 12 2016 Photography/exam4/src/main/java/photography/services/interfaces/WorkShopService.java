package photography.services.interfaces;

import photography.domain.dto.importDto.workshops.WorkshopRootDto;
import photography.domain.dto.query4.LocRootExportDto;

public interface WorkShopService {
    String importWorkshops(WorkshopRootDto workshopRootDto);

    LocRootExportDto query4();
}
