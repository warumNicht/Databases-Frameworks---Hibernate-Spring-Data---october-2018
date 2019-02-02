package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.importDtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.service.interfaces.DistrictService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class DistrictServiceImpl implements DistrictService {
    private DistrictRepository districtRepository;
    private TownRepository townRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;
@Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository,
                               TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil,
                               ModelMapper mapper) {
        this.districtRepository = districtRepository;
    this.townRepository = townRepository;
    this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count()>0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        String content = this.fileUtil.readFile(Constants.DISTRICTS_FILE_PATH);
        return content;
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        DistrictImportDto[] districtImportDtos = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);
        StringBuilder res=new StringBuilder();

        for (DistrictImportDto districtImportDto : districtImportDtos) {
            if(!this.validationUtil.isValid(districtImportDto)){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.townRepository.existsByName(districtImportDto.getTownName())){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            if(this.districtRepository.existsByName(districtImportDto.getName())){
                res.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            District district = this.mapper.map(districtImportDto, District.class);
            Town town = this.townRepository.findByName(districtImportDto.getTownName()).orElse(null);
            district.setTown(town);
            this.districtRepository.saveAndFlush(district);

            res.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,"District",districtImportDto.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
