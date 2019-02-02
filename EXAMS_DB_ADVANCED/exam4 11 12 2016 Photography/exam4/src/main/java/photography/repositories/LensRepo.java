package photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import photography.domain.entities.Lens;
@Repository
public interface LensRepo extends JpaRepository<Lens,Integer> {
}
