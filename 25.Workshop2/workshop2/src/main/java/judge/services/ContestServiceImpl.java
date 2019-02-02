package judge.services;

import com.google.gson.Gson;
import judge.domain.dtos.contestImportDto.ContestImportDto;
import judge.domain.dtos.contestImportDto.StrategyContestDto;
import judge.domain.entities.Category;
import judge.domain.entities.Contest;
import judge.domain.entities.Strategy;
import judge.repositories.CategoryRepository;
import judge.repositories.ContestRepository;
import judge.repositories.StrategyRepository;
import judge.services.inretfaces.ContestService;
import judge.util.FileUtil;
import judge.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;

@Service
public class ContestServiceImpl implements ContestService {
    private ContestRepository contestRepository;
    private CategoryRepository categoryRepository;
    private StrategyRepository strategyRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;

    @Autowired
    public ContestServiceImpl(ContestRepository contestRepository, CategoryRepository categoryRepository,
                              StrategyRepository strategyRepository, FileUtil fileUtil,
                              Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.contestRepository = contestRepository;
        this.categoryRepository = categoryRepository;
        this.strategyRepository = strategyRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public String importContests(String contestFilePath) throws IOException {
        String content = this.fileUtil.readFile(contestFilePath);
        ContestImportDto[] contestImportDtos = this.gson.fromJson(content, ContestImportDto[].class);
        for (ContestImportDto contestImportDto : contestImportDtos) {
            if (this.validationUtil.isValid(contestImportDto)) {
                Contest contest = this.mapper.map(contestImportDto, Contest.class);

                Category category = this.categoryRepository.findByName(contestImportDto.getCategory().getName()).orElse(null);
                contest.setCategory(category);

                contest.setStrategies(new HashSet<>());
                for (StrategyContestDto strategyContestDto : contestImportDto.getAllowedStrategies()) {
                    Strategy strategy = this.strategyRepository.findByName(strategyContestDto.getName()).orElse(null);
                    if(strategy!=null){
                        contest.getStrategies().add(strategy);
                    }
                }
                this.contestRepository.saveAndFlush(contest);

            }
        }

        return null;
    }
}
