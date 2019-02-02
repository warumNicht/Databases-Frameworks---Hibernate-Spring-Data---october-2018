package org.softuni.mostwanted.terminal;

import com.google.gson.Gson;
import org.softuni.mostwanted.domain.dto.exportDto.RacerCarsDto;
import org.softuni.mostwanted.domain.dto.exportDto.RacingTownDto;
import org.softuni.mostwanted.domain.dto.importDto.CarImportDto;
import org.softuni.mostwanted.domain.dto.importDto.DistrictImportDto;
import org.softuni.mostwanted.domain.dto.importDto.RacerImportDto;
import org.softuni.mostwanted.domain.dto.importDto.TownImportDto;
import org.softuni.mostwanted.domain.dto.importDto.xmlDto.RaceEntryImportRootDto;
import org.softuni.mostwanted.domain.dto.importDto.xmlDto.import2.RaceRootDto;
import org.softuni.mostwanted.domain.dto.query3.RootWantedDto;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
@Transactional
public class Terminal implements CommandLineRunner {
    private final static String TOWN_FILE_PATH = "src\\main\\resources\\files\\json\\input\\towns.json";
    private final static String DISTRICT_FILE_PATH = "src\\main\\resources\\files\\json\\input\\districts.json";
    private final static String RACERS_FILE_PATH = "src\\main\\resources\\files\\json\\input\\racers.json";
    private final static String CARS_FILE_PATH = "src\\main\\resources\\files\\json\\input\\cars.json";

    private final static String RACE_ENTRY_FILE_PATH = "src\\main\\resources\\files\\xml\\input\\race-entries.xml";
    private final static String RACES_FILE_PATH = "src\\main\\resources\\files\\xml\\input\\races.xml";

    private final static String PB1_PATH = "src\\main\\resources\\files\\json\\output\\racingTowns.json";
    private final static String PB2_PATH = "src\\main\\resources\\files\\json\\output\\racingCars.json";

    private final static String PB3_PATH = "src\\main\\resources\\files\\xml\\output\\most-wanted.xml";

    private final Gson gson;
    private final FileIO fileUtil;
    private final TownService townService;
    private final DistrictService districtService;
    private final RacerService racerService;
    private final CarService carService;
    private final RaceEntryService raceEntryService;
    private final RaceService raceService;
    private final Parser xmlParser;


    @Autowired
    public Terminal(Gson gson, FileIO fileUtil, TownService townService,
                    DistrictService districtService, RacerService racerService,
                    CarService carService, RaceEntryService raceEntryService, RaceService raceService, Parser xmlParser) {
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.townService = townService;
        this.districtService = districtService;
        this.racerService = racerService;
        this.carService = carService;
        this.raceEntryService = raceEntryService;
        this.raceService = raceService;

        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {
        this.importTowns();
        this.importDistricts();
        this.importRacers();
        this.importCars();
        this.importRaceEntries();
        this.importRaces();
        this.racingTowns();
        this.racingCars();
        this.mostWanted();
    }

    private void importTowns() throws IOException {
        String townFileContent = this.fileUtil.read(TOWN_FILE_PATH);
        TownImportDto[] townImportDtos = this.gson.fromJson(townFileContent, TownImportDto[].class);
        String result = this.townService.importTowns(townImportDtos);
        System.out.println(result);
    }

    private void importDistricts() throws IOException {
        String districtsFileContent = this.fileUtil.read(DISTRICT_FILE_PATH);
        DistrictImportDto[] districtImportDtos = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);
        String result = this.districtService.importDistricts(districtImportDtos);
        System.out.println(result);
    }

    private void importRacers() throws IOException {
        String content = this.fileUtil.read(RACERS_FILE_PATH);
        RacerImportDto[] racerImportDtos = this.gson.fromJson(content, RacerImportDto[].class);
        String result = this.racerService.importRacers(racerImportDtos);
        System.out.println(result);
    }

    private void importCars() throws IOException {
        String content = this.fileUtil.read(CARS_FILE_PATH);
        CarImportDto[] carImportDtos = this.gson.fromJson(content, CarImportDto[].class);
        String res = this.carService.importCars(carImportDtos);
        System.out.println(res);
    }

    private void importRaceEntries() throws IOException, JAXBException {
        String content = this.fileUtil.read(RACE_ENTRY_FILE_PATH);
//        Parser xmlParser=new ParserFileImpl();
        RaceEntryImportRootDto raceEntryImportRootDto = this.xmlParser.read(RaceEntryImportRootDto.class, content);
        String res = this.raceEntryService.importRaceEntries(raceEntryImportRootDto);
        System.out.println(res);
    }

    private void importRaces() throws IOException, JAXBException {
        String content = this.fileUtil.read(RACES_FILE_PATH);
        RaceRootDto read = this.xmlParser.read(RaceRootDto.class, content);
        String res = this.raceService.importRaces(read);
        System.out.println(res);
    }

    private void racingTowns() throws IOException {
        RacingTownDto[] racingTownDtos = this.townService.racingTowns();
        String result = this.gson.toJson(racingTownDtos);
        this.fileUtil.write(result, PB1_PATH);
    }

    private void racingCars() throws IOException {
        RacerCarsDto[] racerCarsDtos = this.racerService.racingCars();
        String res = this.gson.toJson(racerCarsDtos);
        this.fileUtil.write(res, PB2_PATH);
    }

    private void mostWanted() throws IOException, JAXBException {
        RootWantedDto rootWantedDto = this.racerService.mostWanted();
        String result = this.xmlParser.write(rootWantedDto);
        this.fileUtil.write(result, PB3_PATH);
    }
}
