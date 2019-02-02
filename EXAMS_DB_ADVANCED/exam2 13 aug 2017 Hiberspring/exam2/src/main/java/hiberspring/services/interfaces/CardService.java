package hiberspring.services.interfaces;

import hiberspring.domain.dto.importdto.EmployeeCardImportDto;

public interface CardService {
    String importCards(EmployeeCardImportDto[] employeeCardImportDtos);

    EmployeeCardImportDto[] findCardsWithoutUser();
}
