package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.importDtos.raceEntries.EntriesRootImportDto;
import mostwanted.domain.dtos.importDtos.raceEntries.RaceEntryImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.service.interfaces.RaceEntryService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private RaceEntryRepository raceEntryRepository;
    private RacerRepository racerRepository;
    private CarRepository carRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;
    private XmlParser xmlParser;
@Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, RacerRepository racerRepository,
                                CarRepository carRepository,
                                FileUtil fileUtil, ValidationUtil validationUtil,
                                ModelMapper mapper, XmlParser xmlParser) {
        this.raceEntryRepository = raceEntryRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    this.xmlParser = xmlParser;
}

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count()>0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        String content = this.fileUtil.readFile(Constants.RACE_ENTRIES_FILE_PATH);
        return content;
    }

    @Override
    public String importRaceEntries() throws JAXBException {
        EntriesRootImportDto entriesRootImportDto = this.xmlParser.parseXml(EntriesRootImportDto.class, Constants.RACE_ENTRIES_FILE_PATH);
        StringBuilder res=new StringBuilder();
        int id=1;
        for (RaceEntryImportDto raceEntryImportDto : entriesRootImportDto.getRaceEntryImportDtos()) {

            if(!this.carRepository.existsById(raceEntryImportDto.getCarId())){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.racerRepository.existsByName(raceEntryImportDto.getRacer())){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            RaceEntry raceEntry = this.mapper.map(raceEntryImportDto, RaceEntry.class);
            Car car = this.carRepository.findById(raceEntryImportDto.getCarId()).orElse(null);
            Racer racer = this.racerRepository.findByName(raceEntryImportDto.getRacer()).orElse(null);
            raceEntry.setCar(car);
            raceEntry.setRacer(racer);
            raceEntry.setRace(null);

            this.raceEntryRepository.saveAndFlush(raceEntry);

            res.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,"RaceEntry",""+id++))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
