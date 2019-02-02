package cars.repositories;

import cars.domain.entities.Car;
import cars.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale,Long> {
    boolean existsByCar(Car car);

}
