package hiberspring.repositories;


import hiberspring.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {

    Optional<Town> findByName(String name);
    @Query(value = "SELECT t.name,t.population,SUM(p.clients) AS con " +
            "FROM Town t " +
            "JOIN t.branches AS b " +
            "JOIN b.products AS p " +
            "GROUP BY t.id " +
            "ORDER BY con DESC ")
    List<Object[]> getTownStatistics();

    @Query(value = "SELECT t.name,t.population,SUM(p.clients) AS con " +
            "FROM Product AS p " +
            "JOIN p.branch AS b " +
            "JOIN b.town AS t " +
            "GROUP BY t.id " +
            "ORDER BY con DESC ")
    List<Object[]> getTownStatistics2();
}
