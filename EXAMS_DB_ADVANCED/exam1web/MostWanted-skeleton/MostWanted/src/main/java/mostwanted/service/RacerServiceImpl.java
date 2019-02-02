package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.importDtos.RacerImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.service.interfaces.RacerService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RacerServiceImpl implements RacerService {
    private RacerRepository racerRepository;
    private TownRepository townRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository,
                            TownRepository townRepository, FileUtil fileUtil,
                            Gson gson, ValidationUtil validationUtil,
                            ModelMapper mapper) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        String content = this.fileUtil.readFile(Constants.RACERS_FILE_PATH);
        return content;
    }

    @Override
    public String importRacers(String racersFileContent) {
        RacerImportDto[] racerImportDtos = this.gson.fromJson(racersFileContent, RacerImportDto[].class);
        StringBuilder res = new StringBuilder();

        for (RacerImportDto racerImportDto : racerImportDtos) {
            if (!this.validationUtil.isValid(racerImportDto)) {
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if (this.racerRepository.existsByName(racerImportDto.getName())) {
                res.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if (!this.townRepository.existsByName(racerImportDto.getHomeTown())) {
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.townRepository.findByName(racerImportDto.getHomeTown()).orElse(null);
            Racer racer = this.mapper.map(racerImportDto, Racer.class);
            racer.setHomeTown(town);
            racer.setCars(new TreeSet<>());
            this.racerRepository.saveAndFlush(racer);

            res.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Racer", racerImportDto.getName()))
                    .append(System.lineSeparator());
        }


        return res.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        List<Racer> racers = this.racerRepository.allByCars();
        StringBuilder res=new StringBuilder();
        for (Racer racer : racers) {
            res.append(String.format("Name: %s",racer.getName()))
                    .append(System.lineSeparator());
            if(racer.getAge()!=null){
                res.append(String.format("Age: %d",racer.getAge()))
                        .append(System.lineSeparator());
            }
            res.append("Cars:").append(System.lineSeparator());

            for (Car car : racer.getCars()) {
                res.append(String.format("\t%s %s %d",
                        car.getBrand(),car.getModel(),car.getYearOfProduction()))
                        .append(System.lineSeparator());
            }
            res.append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
