package org.softuni.mostwanted.services;


import org.hibernate.Hibernate;
import org.softuni.mostwanted.domain.dto.exportDto.RacingTownDto;
import org.softuni.mostwanted.domain.dto.importDto.TownImportDto;
import org.softuni.mostwanted.domain.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.interfaces.TownService;
import org.softuni.mostwanted.util.MessagesText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {


    private final TownRepository townRepository;
    private final ModelParser mapper;
    private final ValidationUtil validationUtil;


    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelParser mapper, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importTowns(TownImportDto[] townImportDtos) {
        StringBuilder res=new StringBuilder();
        for (TownImportDto townImportDto : townImportDtos) {

            if(!this.validationUtil.isValid(townImportDto)){
                res.append(MessagesText.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(this.townRepository.existsByName(townImportDto.getName())){
                res.append(MessagesText.DUPLICATE_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.mapper.convert(townImportDto, Town.class);
            this.townRepository.saveAndFlush(town);
            res.append(String.format("Succesfully imported Town – %s.",town.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    @Override
    public RacingTownDto[] racingTowns() {
        List<Town> towns = this.townRepository.racingTowns();
        RacingTownDto[] res=new RacingTownDto[towns.size()];
        for (int i = 0; i < towns.size(); i++) {
            Town town = towns.get(i);
            RacingTownDto racingTownDto = new RacingTownDto();
            racingTownDto.setName(town.getName());
            Hibernate.initialize(town.getRacers());
            racingTownDto.setRacers(town.getRacers().size());
            res[i]=racingTownDto;
        }
        return res;
    }
}
