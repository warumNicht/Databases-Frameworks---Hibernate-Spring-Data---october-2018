package mostwanted.repository;

import mostwanted.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    // TODO : Implement me

    boolean existsById(Integer id);

    Optional<Car> findById(Integer id);
}
