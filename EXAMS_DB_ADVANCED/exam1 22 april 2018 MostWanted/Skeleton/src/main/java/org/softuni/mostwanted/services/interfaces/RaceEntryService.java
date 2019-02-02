package org.softuni.mostwanted.services.interfaces;

import org.softuni.mostwanted.domain.dto.importDto.xmlDto.RaceEntryImportRootDto;

public interface RaceEntryService {
    String importRaceEntries(RaceEntryImportRootDto raceEntryImportRootDto);
}
