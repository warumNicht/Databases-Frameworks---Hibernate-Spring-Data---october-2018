package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.importDtos.TownImportDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.service.interfaces.TownService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TownServiceImpl implements TownService {
    private TownRepository townRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;



@Autowired
    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, Gson gson,
                           ValidationUtil validationUtil, ModelMapper mapper) {
    this.townRepository = townRepository;
    this.fileUtil = fileUtil;
    this.gson = gson;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
}

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count()>0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        String content = this.fileUtil.readFile(Constants.TOWNS_FILE_PATH);
        return content;
    }

    @Override
    public String importTowns(String townsFileContent) {
        TownImportDto[] townImportDtos = this.gson.fromJson(townsFileContent, TownImportDto[].class);

        StringBuilder res=new StringBuilder();

        for (TownImportDto townImportDto : townImportDtos) {
            if (!this.validationUtil.isValid(townImportDto)){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if (this.townRepository.existsByName(townImportDto.getName())){
                res.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town town = this.mapper.map(townImportDto, Town.class);
            this.townRepository.saveAndFlush(town);

            res.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,"Town",townImportDto.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    @Override
    public String exportRacingTowns() {
        List<Object[]> allByRacersCount = this.townRepository.findAllByRacersCount();
        StringBuilder res=new StringBuilder();
        for (Object[] objects : allByRacersCount) {
            res.append(String.format("Name: %s",(String)objects[0]))
                    .append(System.lineSeparator());
            res.append(String.format("Racers: %s",(Long)objects[1]))
                    .append(System.lineSeparator());
            res.append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
