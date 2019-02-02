package pb2_carsDealer.services.interfaces;

import pb2_carsDealer.domain.dto.query2.AllCarsToyotaDto;
import pb2_carsDealer.domain.dto.query2.CarModelDto;
import pb2_carsDealer.domain.dto.query4.AllCarsWithPartsDto;
import pb2_carsDealer.domain.dto.seedDtos.CarRootDto;

import java.util.List;

public interface CarService {
    void seedCars(CarRootDto carSeedDtos);

    AllCarsToyotaDto getAllToyota();

    AllCarsWithPartsDto getCarsWithParts();
}
