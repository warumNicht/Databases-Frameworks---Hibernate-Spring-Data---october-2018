package org.softuni.mostwanted.services;

import org.softuni.mostwanted.domain.dto.importDto.CarImportDto;
import org.softuni.mostwanted.domain.entities.Car;
import org.softuni.mostwanted.domain.entities.Racer;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.interfaces.CarService;
import org.softuni.mostwanted.util.MessagesText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final ModelParser mapper;
    private final ValidationUtil validationUtil;
@Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, ModelParser mapper, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importCars(CarImportDto[] carImportDtos) {
        StringBuilder res=new StringBuilder();
        for (CarImportDto carImportDto : carImportDtos) {
            if(!this.validationUtil.isValid(carImportDto)){
                res.append(MessagesText.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Racer racer = this.racerRepository.findByName(carImportDto.getRacerName()).orElse(null);
            Car car = this.mapper.convert(carImportDto, Car.class);
            car.setDriver(racer);
            racer.getCars().add(car);

            this.carRepository.saveAndFlush(car);

            res.append(String.format("Succesfully imported Car – %s %s @ %d.",
                    car.getBrand(),car.getModel(),car.getYearOfProduction()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
