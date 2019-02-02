package mostwanted.repository;

import mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town,Integer> {
    // TODO : Implement me

    boolean existsByName(String name);

    Optional<Town> findByName(String name);

    @Query(value = "SELECT t.name,COUNT(r) AS con FROM Town t ,Racer AS r " +
            "WHERE t =r.homeTown " +
            "GROUP BY t.id " +
            "ORDER BY con DESC, t.name")
    List<Object[]> findAllByRacersCount();
}
