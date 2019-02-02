package hiberspring.services;

import hiberspring.domain.dto.query3.TownStatisticsElementDto;
import hiberspring.domain.dto.query3.TownStatisticsRootDto;
import hiberspring.domain.dto.importdto.TownImportDto;
import hiberspring.domain.entities.Town;
import hiberspring.repositories.TownRepository;
import hiberspring.services.interfaces.TownService;
import hiberspring.util.TextUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TownServiceImpl implements TownService {
    private TownRepository townRepository;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;
@Autowired
    public TownServiceImpl(TownRepository townRepository,
                           ValidationUtil validationUtil, ModelMapper mapper) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }
    @Override
    public String importTowns(TownImportDto[] townImportDtos){
        StringBuilder res=new StringBuilder();
        for (TownImportDto townImportDto : townImportDtos) {
            if(!this.validationUtil.isValid(townImportDto)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.mapper.map(townImportDto, Town.class);
            this.townRepository.saveAndFlush(town);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_2PARAM,town.getName(), town.getPopulation()))
                    .append(System.lineSeparator());
        }

        return res.toString().trim();
    }
    @Override
    public TownStatisticsRootDto getTownStatistics(){
        List<Object[]> townStatistics = this.townRepository.getTownStatistics();
        List<Object[]> townStatistics2 = this.townRepository.getTownStatistics2();
        TownStatisticsRootDto res=new TownStatisticsRootDto();
        List<TownStatisticsElementDto> townStatisticsElementDtos=new ArrayList<>();
        for (Object[] townStatistic : townStatistics) {
            String name=(String)townStatistic[0];
            Integer population=(Integer)townStatistic[1];
            Long clients=(Long)townStatistic[2];
            TownStatisticsElementDto elementDto=new TownStatisticsElementDto();
            elementDto.setName(name);
            elementDto.setPopulation(population);
            elementDto.setClients(clients);
            townStatisticsElementDtos.add(elementDto);
        }
        res.setElementDtos(townStatisticsElementDtos);
        return res;
    }
}
