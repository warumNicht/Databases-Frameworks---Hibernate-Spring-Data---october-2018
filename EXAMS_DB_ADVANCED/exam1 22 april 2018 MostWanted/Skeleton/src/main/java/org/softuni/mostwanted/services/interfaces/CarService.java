package org.softuni.mostwanted.services.interfaces;

import org.softuni.mostwanted.domain.dto.importDto.CarImportDto;

public interface CarService {
    String importCars(CarImportDto[] carImportDtos);
}
