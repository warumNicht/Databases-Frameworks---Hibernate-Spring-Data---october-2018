package org.softuni.mostwanted.services.interfaces;

import org.softuni.mostwanted.domain.dto.importDto.DistrictImportDto;

public interface DistrictService {
    String importDistricts(DistrictImportDto[] districtImportDtos);


}
