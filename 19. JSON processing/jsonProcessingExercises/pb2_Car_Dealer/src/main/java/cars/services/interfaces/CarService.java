package cars.services.interfaces;

import cars.domain.dto.CarModelDto;
import cars.domain.dto.query4.CarWithPartsDto;
import cars.domain.dto.seedDtos.CarSeedDto;

import java.util.List;

public interface CarService {
    void seedCars(CarSeedDto[] carSeedDtos);

    List<CarModelDto> getAllToyota();

    List<CarWithPartsDto> getCarsWithParts();
}
