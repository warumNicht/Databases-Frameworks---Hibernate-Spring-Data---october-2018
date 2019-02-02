package photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import photography.domain.entities.Location;

import java.util.List;
import java.util.Optional;

public interface LocationRepo extends JpaRepository<Location,Integer> {
    boolean existsByName(String name);

    Optional<Location> getByName(String name);

    @Query(value = "SELECT l FROM Location l " +
            "JOIN l.workshops AS w " +
            "JOIN w.participants AS p " +
            "GROUP BY l.id " +
            "HAVING COUNT(w)>=5 ")
    List<Location> allWithWorkshopsWith5Participants();
}
