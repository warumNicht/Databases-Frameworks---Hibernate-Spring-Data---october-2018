package cars.repositories;

import cars.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

}
