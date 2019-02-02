package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.dto.importDto.xmlDto.import2.EntryRaceDto;
import org.softuni.mostwanted.domain.dto.importDto.xmlDto.import2.RaceImportDto;
import org.softuni.mostwanted.domain.dto.importDto.xmlDto.import2.RaceRootDto;
import org.softuni.mostwanted.domain.entities.District;
import org.softuni.mostwanted.domain.entities.Race;
import org.softuni.mostwanted.domain.entities.RaceEntry;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.interfaces.RaceService;
import org.softuni.mostwanted.util.MessagesText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final ModelParser mapper;
    private final ValidationUtil validationUtil;
    private final DistrictRepository districtRepository;
    private final RaceEntryRepository raceEntryRepository;
@Autowired
    public RaceServiceImpl(RaceRepository raceRepository, ModelParser mapper, ValidationUtil validationUtil, DistrictRepository districtRepository, RaceEntryRepository raceEntryRepository) {
        this.raceRepository = raceRepository;
        this.mapper = mapper;
    this.validationUtil = validationUtil;
    this.districtRepository = districtRepository;
    this.raceEntryRepository = raceEntryRepository;
}

    @Override
    public String importRaces(RaceRootDto raceRootDto) {
        StringBuilder res=new StringBuilder();
        int raceIndex=0;
        for (RaceImportDto raceImportDto : raceRootDto.getRaceImportDtos()) {
            if(!this.validationUtil.isValid(raceImportDto)){
                res.append(MessagesText.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            List<District> byName = this.districtRepository.findByName(raceImportDto.getDistrict());
            if(byName.size()!=1){
                res.append(MessagesText.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            District district=byName.get(0);
            Race race = this.mapper.convert(raceImportDto, Race.class);
            race.setDistrict(district);
            Set<RaceEntry> raceEntries=new LinkedHashSet<>();
            for (EntryRaceDto entryRaceDto : raceImportDto.getEntries()) {
                int index=entryRaceDto.getId();
                RaceEntry raceEntry = this.raceEntryRepository.findById(index).orElse(null);
                if(raceEntry!=null){
                    raceEntries.add(raceEntry);
                }
            }
            race.setEntries(raceEntries);
            this.raceRepository.saveAndFlush(race);
            for (RaceEntry raceEntry : raceEntries) {
                raceEntry.setRace(race);
                this.raceEntryRepository.save(raceEntry);
            }
            res.append(String.format("Succesfully imported Race – %d.",++raceIndex))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
