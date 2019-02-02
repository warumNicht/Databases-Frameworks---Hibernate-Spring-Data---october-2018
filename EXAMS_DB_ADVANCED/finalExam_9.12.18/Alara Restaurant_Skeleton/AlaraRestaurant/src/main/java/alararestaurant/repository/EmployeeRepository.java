package alararestaurant.repository;

import alararestaurant.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    boolean existsByName(String name);

    Optional<Employee> findByName(String name);

    @Query(value = "SELECT e FROM  Employee e " +
            "JOIN e.orders AS o " +
            "WHERE e.position.name=:positionName " +
            "GROUP BY e.id " +
            "ORDER BY  e.name, o.id ")
    List<Employee> query2(@Param(value = "positionName") String positionName);
}
