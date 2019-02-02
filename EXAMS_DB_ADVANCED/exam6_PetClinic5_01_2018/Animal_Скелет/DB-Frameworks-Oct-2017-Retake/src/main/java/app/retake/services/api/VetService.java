package app.retake.services.api;

import app.retake.domain.dto.importDtos.vets.VetXMLImportDTO;

public interface VetService {
    void create(VetXMLImportDTO dto);
}
