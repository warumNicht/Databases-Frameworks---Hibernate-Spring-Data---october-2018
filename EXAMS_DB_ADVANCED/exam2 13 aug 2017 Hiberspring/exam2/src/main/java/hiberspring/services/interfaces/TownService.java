package hiberspring.services.interfaces;

import hiberspring.domain.dto.query3.TownStatisticsRootDto;
import hiberspring.domain.dto.importdto.TownImportDto;

public interface TownService {
    String importTowns(TownImportDto[] townImportDtos);

    TownStatisticsRootDto getTownStatistics();
}
