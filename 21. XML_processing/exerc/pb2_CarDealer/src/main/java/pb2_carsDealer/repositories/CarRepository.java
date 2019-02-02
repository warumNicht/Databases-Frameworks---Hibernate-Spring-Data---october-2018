package pb2_carsDealer.repositories;

import pb2_carsDealer.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

}
