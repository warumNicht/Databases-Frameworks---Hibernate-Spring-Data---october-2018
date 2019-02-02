package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.importDtos.racesImport.EntryIdImportDto;
import mostwanted.domain.dtos.importDtos.racesImport.RaceImportDto;
import mostwanted.domain.dtos.importDtos.racesImport.RaceRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.service.interfaces.RaceService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class RaceServiceImpl implements RaceService {
    private RaceRepository raceRepository;
    private RaceEntryRepository raceEntryRepository;
    private DistrictRepository districtRepository;
    private FileUtil fileUtil;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;
    private XmlParser xmlParser;
@Autowired
    public RaceServiceImpl(RaceRepository raceRepository, RaceEntryRepository raceEntryRepository,
                           DistrictRepository districtRepository, FileUtil fileUtil,
                           ModelMapper mapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.raceRepository = raceRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.districtRepository = districtRepository;
        this.fileUtil = fileUtil;
        this.mapper = mapper;
    this.validationUtil = validationUtil;
    this.xmlParser = xmlParser;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count()>0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {

        String content = this.fileUtil.readFile(Constants.RACES_FILE_PATH);
        return content;
    }

    @Override
    public String importRaces() throws JAXBException {
        RaceRootDto raceRootDto = this.xmlParser.parseXml(RaceRootDto.class, Constants.RACES_FILE_PATH);

        StringBuilder res = new StringBuilder();
        int id=1;
        for (RaceImportDto raceImportDto : raceRootDto.getRaceImportDtos()) {
            if(!this.validationUtil.isValid(raceImportDto)){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.districtRepository.existsByName(raceImportDto.getDistrictName())){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Race race = new Race();
            race.setLaps(raceImportDto.getLaps());
            race.setEntries(new HashSet<>());
            District district = this.districtRepository.getByName(raceImportDto.getDistrictName()).orElse(null);
            race.setDistrict(district);
            Set<RaceEntry>  raceEntries=new LinkedHashSet<>();
            for (EntryIdImportDto entryIdImportDto : raceImportDto.getEntryIdImportDtos()) {
                RaceEntry raceEntry = this.raceEntryRepository.findById(entryIdImportDto.getId()).orElse(null);
                if(raceEntry!=null){
                    raceEntry.setRace(race);
                    raceEntries.add(raceEntry);
                }
            }
            race.setEntries(raceEntries);
            race = this.raceRepository.saveAndFlush(race);

            this.raceEntryRepository.saveAll(raceEntries);

            res.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,"Race",""+id++))
                    .append(System.lineSeparator());

        }
        return res.toString().trim();
    }
}
