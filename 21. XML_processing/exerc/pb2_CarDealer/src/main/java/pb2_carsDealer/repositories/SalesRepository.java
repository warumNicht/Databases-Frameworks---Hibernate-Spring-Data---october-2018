package pb2_carsDealer.repositories;

import pb2_carsDealer.domain.entities.Car;
import pb2_carsDealer.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sale,Long> {
    boolean existsByCar(Car car);

}
