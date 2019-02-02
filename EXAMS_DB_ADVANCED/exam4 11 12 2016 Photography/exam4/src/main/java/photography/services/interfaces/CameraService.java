package photography.services.interfaces;

import photography.domain.dto.importDto.CameraImportDto;

public interface CameraService {
    String importCameras(CameraImportDto[] cameraImportDtos);
}
