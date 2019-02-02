package pb2_carsDealer.repositories;

import pb2_carsDealer.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsRepository extends JpaRepository<Part,Long> {
}
