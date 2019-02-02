package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.dto.importDto.DistrictImportDto;
import org.softuni.mostwanted.domain.entities.District;
import org.softuni.mostwanted.domain.entities.Town;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.interfaces.DistrictService;
import org.softuni.mostwanted.util.MessagesText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final ModelParser mapper;
    private final ValidationUtil validationUtil;
@Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, ModelParser mapper, ValidationUtil validationUtil) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importDistricts(DistrictImportDto[] districtImportDtos) {
        StringBuilder res=new StringBuilder();
        for (DistrictImportDto districtImportDto : districtImportDtos) {
            if(!this.validationUtil.isValid(districtImportDto)){
                res.append(MessagesText.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.townRepository.findByName(districtImportDto.getTownName()).orElse(null);
            if(town==null){
                res.append(MessagesText.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(this.districtRepository.existsByNameAndTown(districtImportDto.getName(),town)){
                res.append(MessagesText.DUPLICATE_MESSAGE).append(System.lineSeparator());
                continue;
            }
            District district = this.mapper.convert(districtImportDto, District.class);
            district.setTown(town);
            this.districtRepository.saveAndFlush(district);
            res.append(String.format("Succesfully imported District – %s.",district.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
