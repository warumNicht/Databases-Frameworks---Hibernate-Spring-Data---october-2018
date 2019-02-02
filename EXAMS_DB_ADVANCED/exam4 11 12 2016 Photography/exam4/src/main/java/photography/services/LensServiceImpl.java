package photography.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.domain.dto.importDto.LensImportDto;
import photography.domain.entities.Lens;
import photography.repositories.LensRepo;
import photography.services.interfaces.LensService;
import photography.util.TextUtil;
import photography.util.ValidationUtil;

@Service
public class LensServiceImpl implements LensService {
    private LensRepo lensRepo;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;

    @Autowired
    public LensServiceImpl(LensRepo lensRepo, ModelMapper mapper, ValidationUtil validationUtil) {
        this.lensRepo = lensRepo;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importLenses(LensImportDto[] lensImportDtos) {
        StringBuilder res = new StringBuilder();
        for (LensImportDto lensImportDto : lensImportDtos) {
            if(!this.validationUtil.isValid(lensImportDto)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Lens lens = this.mapper.map(lensImportDto, Lens.class);
            this.lensRepo.saveAndFlush(lens);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,
                    String.format("%s %dmm f%.1f",
                            lens.getMake(),lens.getFocalLength(),
                            lens.getMaxAperture()
                            )))
            .append(System.lineSeparator());

        }
        return res.toString().trim();
    }
}
