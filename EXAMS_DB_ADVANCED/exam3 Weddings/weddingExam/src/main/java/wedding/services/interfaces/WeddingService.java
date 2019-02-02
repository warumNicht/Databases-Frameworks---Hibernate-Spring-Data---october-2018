package wedding.services.interfaces;

import wedding.domain.dto.importDto.weddingImport.WeddingImportDto;
import wedding.domain.dto.query2.WeddingExportDto;

public interface WeddingService {
    String importWeddings(WeddingImportDto[] weddingImportDtos);

    void  setVenuesToWeddings();

    WeddingExportDto[] query2();
}
