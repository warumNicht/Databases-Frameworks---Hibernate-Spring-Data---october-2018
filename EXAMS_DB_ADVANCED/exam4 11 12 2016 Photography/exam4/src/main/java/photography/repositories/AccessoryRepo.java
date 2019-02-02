package photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import photography.domain.entities.Accessory;

public interface AccessoryRepo extends JpaRepository<Accessory,Integer> {
}
