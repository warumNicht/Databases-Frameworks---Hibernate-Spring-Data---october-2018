package cars.repositories;

import cars.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsRepository extends JpaRepository<Part,Long> {
}
