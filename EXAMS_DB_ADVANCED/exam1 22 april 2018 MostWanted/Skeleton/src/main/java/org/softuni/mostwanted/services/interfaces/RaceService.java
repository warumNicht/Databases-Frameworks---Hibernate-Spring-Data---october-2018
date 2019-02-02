package org.softuni.mostwanted.services.interfaces;

import org.softuni.mostwanted.domain.dto.importDto.xmlDto.import2.RaceRootDto;

public interface RaceService {
    String importRaces(RaceRootDto raceRootDto);
}
