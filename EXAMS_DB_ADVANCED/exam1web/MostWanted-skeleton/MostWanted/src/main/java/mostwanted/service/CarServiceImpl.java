package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.importDtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.service.interfaces.CarService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private RacerRepository racerRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper mapper;
@Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository,
                          FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.carRepository = carRepository;
    this.racerRepository = racerRepository;
    this.fileUtil = fileUtil;
    this.gson = gson;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
}

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count()>0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        String content = this.fileUtil.readFile(Constants.CARS_FILE_PATH);
        return content;
    }

    @Override
    public String importCars(String carsFileContent) {
        CarImportDto[] carImportDtos = this.gson.fromJson(carsFileContent, CarImportDto[].class);

        StringBuilder res = new StringBuilder();
        for (CarImportDto carImportDto : carImportDtos) {
            if(!this.validationUtil.isValid(carImportDto)){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.racerRepository.existsByName(carImportDto.getRacerName())){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Car car = this.mapper.map(carImportDto, Car.class);
            Racer racer = this.racerRepository.findByName(carImportDto.getRacerName()).orElse(null);
            car.setRacer(racer);

           this.carRepository.saveAndFlush(car);

            res.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Car",
                    String.format("%s %s @ %d",car.getBrand(),car.getModel(),
                            car.getYearOfProduction())))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
