package mostwanted.repository;

import mostwanted.domain.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RacerRepository extends JpaRepository<Racer,Integer> {
    // TODO : Implement me
    boolean existsByName(String name);

    Optional<Racer> findByName(String name);

    @Query(value = "SELECT r FROM  Racer  r " +
            "JOIN r.cars AS c " +
            "GROUP BY r.id " +
            "ORDER BY COUNT(c) DESC,r.name ")
    List<Racer> allByCars();


}
