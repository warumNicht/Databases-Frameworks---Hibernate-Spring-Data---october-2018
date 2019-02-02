package wedding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wedding.domain.entities.Agency;

import java.util.List;
@Repository
public interface AgencyRepository extends JpaRepository<Agency,Integer> {
    List<Agency> findByName(String name);

    @Query(value = "SELECT a FROM Agency a ORDER BY a.employeeCount DESC, a.name")
    List<Agency> findAllByIdOrOrderByEmployeeCountDescNameAsc();

    @Query(value = "SELECT a.town FROM Agency a " +
            "JOIN a.weddings AS w " +
            "GROUP BY a.town " +
            "HAVING COUNT(w)>=2 AND LENGTH(a.town)>=6 " )
    List<Object> findTowns();

    @Query(value = "SELECT a FROM Agency a " +
            "JOIN a.weddings AS w " +
            "WHERE a.town =:townName " +
            "GROUP BY a.id " +
            "HAVING COUNT(w)>=2 ")
    List<Agency> getByTownAndWeddingCount(@Param(value = "townName")String townName);
}
