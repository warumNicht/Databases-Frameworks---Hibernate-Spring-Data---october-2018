package cars.services;

import cars.domain.dto.CarModelDto;
import cars.domain.dto.query4.CarWithPartsDto;
import cars.domain.dto.seedDtos.CarSeedDto;
import cars.domain.entities.Car;
import cars.domain.entities.Part;
import cars.repositories.CarRepository;
import cars.repositories.PartsRepository;
import cars.services.interfaces.CarService;
import cars.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private PartsRepository partsRepository;
    private ValidatorUtil validatorUtil;
    private ModelMapper mapper;
@Autowired
    public CarServiceImpl(CarRepository carRepository, PartsRepository partsRepository, ValidatorUtil validatorUtil, ModelMapper mapper) {
        this.carRepository = carRepository;
        this.partsRepository = partsRepository;
        this.validatorUtil = validatorUtil;
        this.mapper = mapper;
    }

    @Override
    public void seedCars(CarSeedDto[] carSeedDtos) {
        if(this.carRepository.count()!=0){
            return;
        }

        for (CarSeedDto carSeedDto : carSeedDtos) {
            if(!this.validatorUtil.isValid(carSeedDto)){
                this.validatorUtil.getViolations(carSeedDto)
                        .forEach(v-> System.out.println(v.getMessage()));
                System.out.println();
                continue;
            }
            Car car = this.mapper.map(carSeedDto, Car.class);
            Set<Part>randomParts= this.getRandomSetOfParts();
            car.setParts(randomParts);

            this.carRepository.saveAndFlush(car);
        }
    }

    @Override
    public List<CarModelDto> getAllToyota() {
        List<Car> toyotas = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarModelDto> carModelDtos = toyotas.stream()
                .map(t -> this.mapper.map(t, CarModelDto.class))
                .collect(Collectors.toList());
        return carModelDtos;
    }

    @Override
    public List<CarWithPartsDto> getCarsWithParts() {
        List<Car> cars = this.carRepository.findAll();
        return cars.stream()
                .map(car->this.mapper.map(car,CarWithPartsDto.class))
                .collect(Collectors.toList());
    }

    private void printPartsIds(Set<Part> randomParts) {
        randomParts.stream()
                .sorted((x,y)->Long.compare(x.getId(),y.getId()))
                .forEach(p-> System.out.println(String.format("%d [%d], ",p.getId(),p.hashCode())));
        System.out.println();
    }

    private Set<Part> getRandomSetOfParts() {
        long partsCount=this.partsRepository.count();
        Set<Part> result=new HashSet<>();
        Random random=new Random();
        int randomCount=random.nextInt(11)+10;
        for (int i = 0; i < randomCount; i++) {
            long randomPartId=random.nextInt((int)partsCount)+1;
            Part part = this.partsRepository.findById(randomPartId).orElse(null);
            result.add(part);
        }
        return result;
    }
}
