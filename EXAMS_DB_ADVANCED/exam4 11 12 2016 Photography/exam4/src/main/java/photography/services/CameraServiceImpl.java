package photography.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import photography.domain.dto.importDto.CameraImportDto;
import photography.domain.entities.cameras.DSLRCamera;
import photography.domain.entities.cameras.MirrorlessCamera;
import photography.repositories.CameraRepo;
import photography.services.interfaces.CameraService;
import photography.util.TextUtil;
import photography.util.ValidationUtil;

@Service
public class CameraServiceImpl implements CameraService {
    private CameraRepo cameraRepo;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;
@Autowired
    public CameraServiceImpl(CameraRepo cameraRepo, ModelMapper mapper, ValidationUtil validationUtil) {
        this.cameraRepo = cameraRepo;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importCameras(CameraImportDto[] cameraImportDtos){
        StringBuilder res = new StringBuilder();

        for (CameraImportDto cameraImportDto : cameraImportDtos) {
            if(!this.validationUtil.isValid(cameraImportDto)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(cameraImportDto.getType().equals("DSLR")){
                DSLRCamera dslrCamera = this.mapper.map(cameraImportDto, DSLRCamera.class);
                this.cameraRepo.saveAndFlush(dslrCamera);

                res.append(String.format(TextUtil.SUCCESS_MESSAGE_3PARAM,
                        cameraImportDto.getType(),dslrCamera.getMake(), dslrCamera.getModel()))
                        .append(System.lineSeparator());

            }else {
                MirrorlessCamera mirrorlessCamera = this.mapper.map(cameraImportDto, MirrorlessCamera.class);
                this.cameraRepo.saveAndFlush(mirrorlessCamera);

                res.append(String.format(TextUtil.SUCCESS_MESSAGE_3PARAM,
                        cameraImportDto.getType(),mirrorlessCamera.getMake(), mirrorlessCamera.getModel()))
                        .append(System.lineSeparator());
            }

        }
        return res.toString().trim();
    }
}
