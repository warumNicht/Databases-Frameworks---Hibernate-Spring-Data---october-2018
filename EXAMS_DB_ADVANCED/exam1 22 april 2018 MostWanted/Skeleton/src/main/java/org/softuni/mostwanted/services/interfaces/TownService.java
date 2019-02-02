package org.softuni.mostwanted.services.interfaces;

import org.softuni.mostwanted.domain.dto.exportDto.RacingTownDto;
import org.softuni.mostwanted.domain.dto.importDto.TownImportDto;
import org.springframework.stereotype.Service;


public interface TownService {
    String importTowns(TownImportDto[] townImportDtos);

    RacingTownDto[] racingTowns();
}
