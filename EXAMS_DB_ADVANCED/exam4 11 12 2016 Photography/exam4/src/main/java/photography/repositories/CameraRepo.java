package photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import photography.domain.entities.cameras.Camera;

public interface CameraRepo extends JpaRepository<Camera,Integer> {
}
