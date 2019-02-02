package org.softuni.mostwanted.services;

import org.hibernate.Hibernate;
import org.softuni.mostwanted.domain.dto.exportDto.RacerCarsDto;
import org.softuni.mostwanted.domain.dto.exportDto.RacingCarDto;
import org.softuni.mostwanted.domain.dto.importDto.RacerImportDto;
import org.softuni.mostwanted.domain.dto.query3.EntryDto;
import org.softuni.mostwanted.domain.dto.query3.RootWantedDto;
import org.softuni.mostwanted.domain.dto.query3.WanterRacerDto;
import org.softuni.mostwanted.domain.entities.RaceEntry;
import org.softuni.mostwanted.domain.entities.Racer;
import org.softuni.mostwanted.domain.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.interfaces.RacerService;
import org.softuni.mostwanted.util.MessagesText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RacerServiceImpl implements RacerService {
    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final ModelParser mapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, ModelParser mapper, ValidationUtil validationUtil) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importRacers(RacerImportDto[] racerImportDtos) {
        StringBuilder res = new StringBuilder();
        for (RacerImportDto racerImportDto : racerImportDtos) {
            if (!this.validationUtil.isValid(racerImportDto)) {
                res.append(MessagesText.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.townRepository.findByName(racerImportDto.getHomeTown()).orElse(null);
            if (this.racerRepository.existsByName(racerImportDto.getName())) {
                res.append(MessagesText.DUPLICATE_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Racer racer = this.mapper.convert(racerImportDto, Racer.class);
            racer.setHomeTown(town);
            town.getRacers().add(racer);
            this.racerRepository.saveAndFlush(racer);
            res.append(String.format("Succesfully imported Racer – %s.", racer.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    @Override
    public RacerCarsDto[] racingCars() {
        List<Racer> racers = this.racerRepository.racingCars();
        RacerCarsDto[] res=new RacerCarsDto[racers.size()];

        for (int i = 0; i < racers.size(); i++) {
            Racer racer = racers.get(i);
            RacerCarsDto racerCarsDto=new RacerCarsDto();
            racerCarsDto.setName(racer.getName());
            if(racer.getAge()!=0){
                racerCarsDto.setAge(racer.getAge());
            }
            List<RacingCarDto> cars = racer.getCars()
                    .stream()
                    .map(c -> new RacingCarDto(String.format("%s %s %d", c.getBrand(), c.getModel(), c.getYearOfProduction())))
                    .collect(Collectors.toList());
            racerCarsDto.setCars(cars);
            res[i]=racerCarsDto;
        }
        return res;
    }

    @Override
    public RootWantedDto mostWanted() {
        Pageable pageable = PageRequest.of(0,1);
        List<Racer> racers = this.racerRepository.mostWanted2(pageable);
        Racer racer = racers.get(0);

        List<RaceEntry> collect = racer.getEntries().stream()
                .sorted((x, y) -> {
                    return Double.compare(x.getFinishedTime(), y.getFinishedTime());
                })
                .collect(Collectors.toList());

        List<EntryDto> entryDtos = collect.stream()
                .map(s -> {
                    EntryDto curr = new EntryDto();
                    curr.setCar(String.format("%s %s @ %d", s.getCar().getBrand(),
                            s.getCar().getModel(), s.getCar().getYearOfProduction()));
                    curr.setFinishedTime(s.getFinishedTime());
                    return curr;
                })
                .collect(Collectors.toList());
        WanterRacerDto wanterRacerDto=new WanterRacerDto();
        wanterRacerDto.setName(racer.getName());
        wanterRacerDto.setEntries(entryDtos);

        RootWantedDto rootWantedDto=new RootWantedDto();
        rootWantedDto.setWanterRacerDto(wanterRacerDto);

        return rootWantedDto;
    }
}
