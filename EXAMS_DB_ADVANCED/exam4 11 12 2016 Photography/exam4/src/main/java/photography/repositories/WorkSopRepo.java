package photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import photography.domain.entities.Workshop;

public interface WorkSopRepo extends JpaRepository<Workshop,Integer> {
}
