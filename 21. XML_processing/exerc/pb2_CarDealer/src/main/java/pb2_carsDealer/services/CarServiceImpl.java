package pb2_carsDealer.services;

import pb2_carsDealer.domain.dto.query2.AllCarsToyotaDto;
import pb2_carsDealer.domain.dto.query2.CarModelDto;
import pb2_carsDealer.domain.dto.query4.AllCarsWithPartsDto;
import pb2_carsDealer.domain.dto.query4.CarWithPartsDto;
import pb2_carsDealer.domain.dto.seedDtos.CarRootDto;
import pb2_carsDealer.domain.dto.seedDtos.CarSeedDto;
import pb2_carsDealer.domain.entities.Car;
import pb2_carsDealer.domain.entities.Part;
import pb2_carsDealer.repositories.CarRepository;
import pb2_carsDealer.repositories.PartsRepository;
import pb2_carsDealer.services.interfaces.CarService;
import pb2_carsDealer.util.ValidatorUtil;
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
    public void seedCars(CarRootDto carSeedDtos) {
        if(this.carRepository.count()!=0){
            return;
        }

        for (CarSeedDto carSeedDto : carSeedDtos.getCarSeedDtos()) {
            if(!this.validatorUtil.isValid(carSeedDto)){
                this.validatorUtil.getViolations(carSeedDto)
                        .forEach(v-> System.out.println(v.getMessage()));
                System.out.println();
                continue;
            }
            Car car = this.mapper.map(carSeedDto, Car.class);
            Set<Part> randomParts= this.getRandomSetOfParts();
            car.setParts(randomParts);

            this.carRepository.saveAndFlush(car);
        }
    }

    @Override
    public AllCarsToyotaDto getAllToyota() {
        List<Car> toyotas = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarModelDto> carModelDtos = toyotas.stream()
                .map(t -> this.mapper.map(t, CarModelDto.class))
                .collect(Collectors.toList());
        AllCarsToyotaDto result=new AllCarsToyotaDto();
        result.setCarModelDtos(carModelDtos);
        return result;
    }

    @Override
    public AllCarsWithPartsDto getCarsWithParts() {
        List<Car> cars = this.carRepository.findAll();
        AllCarsWithPartsDto result=new AllCarsWithPartsDto();
        List<CarWithPartsDto> carWithPartsDtos = cars.stream()
                .map(car -> this.mapper.map(car, CarWithPartsDto.class))
                .collect(Collectors.toList());
        result.setCars(carWithPartsDtos);
        return result;
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
