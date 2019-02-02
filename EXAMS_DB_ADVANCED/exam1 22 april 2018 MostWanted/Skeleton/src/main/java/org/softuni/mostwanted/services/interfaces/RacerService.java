package org.softuni.mostwanted.services.interfaces;

import org.softuni.mostwanted.domain.dto.exportDto.RacerCarsDto;
import org.softuni.mostwanted.domain.dto.importDto.RacerImportDto;
import org.softuni.mostwanted.domain.dto.query3.RootWantedDto;

public interface RacerService {
    String importRacers(RacerImportDto[] racerImportDtos);

    RacerCarsDto[] racingCars();

    RootWantedDto mostWanted();
}
