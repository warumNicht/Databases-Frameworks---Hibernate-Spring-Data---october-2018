package judge.services;

import com.google.gson.Gson;
import judge.domain.dtos.StrategyImportDto;
import judge.domain.entities.Strategy;
import judge.repositories.StrategyRepository;
import judge.services.inretfaces.StrategyService;
import judge.util.FileUtil;
import judge.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StrategyServiceImpl implements StrategyService {
    private StrategyRepository strategyRepository;
    private  FileUtil fileUtil;
    private Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;
@Autowired
    public StrategyServiceImpl(StrategyRepository strategyRepository,
                               FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.strategyRepository = strategyRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public String importStrategies(String strategyFilePath) throws IOException {
        String content = this.fileUtil.readFile(strategyFilePath);
        StrategyImportDto[] strategyImportDtos = this.gson.fromJson(content, StrategyImportDto[].class);
        for (StrategyImportDto strategyImportDto : strategyImportDtos) {
            if(this.validationUtil.isValid(strategyImportDto)){
                Strategy strategy = this.mapper.map(strategyImportDto, Strategy.class);
                this.strategyRepository.saveAndFlush(strategy);
            }
        }
        return null;
    }
}
