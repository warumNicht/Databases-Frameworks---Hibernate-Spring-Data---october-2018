package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.dto.importDto.xmlDto.RaceEntryImportDto;
import org.softuni.mostwanted.domain.dto.importDto.xmlDto.RaceEntryImportRootDto;
import org.softuni.mostwanted.domain.entities.Car;
import org.softuni.mostwanted.domain.entities.RaceEntry;
import org.softuni.mostwanted.domain.entities.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.interfaces.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private RacerRepository racerRepository;
    private CarRepository carRepository;
    private RaceEntryRepository raceEntryRepository;
    private ModelParser mapper;

    @Autowired
    public RaceEntryServiceImpl(RacerRepository racerRepository, CarRepository carRepository,
                                RaceEntryRepository raceEntryRepository, ModelParser mapper) {
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.mapper = mapper;
    }

    @Override
    public String importRaceEntries(RaceEntryImportRootDto raceEntryImportRootDto) {
        StringBuilder res = new StringBuilder();
        int index=0;
        for (RaceEntryImportDto raceEntryImportDto : raceEntryImportRootDto.getRaceEntryImportDtos()) {
            Car car = this.carRepository.findById(raceEntryImportDto.getCarId()).orElse(null);
            Racer racer = this.racerRepository.findByName(raceEntryImportDto.getRacer()).orElse(null);
            RaceEntry raceEntry=new RaceEntry();
            raceEntry.setFinishedTime(raceEntryImportDto.getFinishedTime());
            raceEntry.setHasFinished(raceEntryImportDto.isHasFinished());
            raceEntry.setRacer(racer);
            raceEntry.setCar(car);
            racer.getEntries().add(raceEntry);
            this.raceEntryRepository.saveAndFlush(raceEntry);

            res.append(String.format("Succesfully imported RaceEntry – %d.",++index))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
