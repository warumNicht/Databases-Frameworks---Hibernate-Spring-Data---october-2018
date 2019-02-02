package wedding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wedding.domain.entities.present.Present;
@Repository
public interface PresentRepo extends JpaRepository<Present,Integer> {
}
