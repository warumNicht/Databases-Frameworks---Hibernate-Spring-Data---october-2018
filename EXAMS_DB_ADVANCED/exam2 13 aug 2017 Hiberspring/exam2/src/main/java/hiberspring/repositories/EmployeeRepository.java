package hiberspring.repositories;


import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsEmployeeByCard(EmployeeCard card);

    @Query(value = "SELECT e FROM Employee  AS e " +
            "JOIN e.branch AS b " +
            "JOIN b.products " +
            "GROUP BY e.id " +
            "ORDER BY concat(e.firstName,' ',e.lastName), LENGTH(e.position) DESC ")
    List<Employee> getWithBranchProduct();

    @Query(value = "SELECT e" +
            " 	FROM Employee AS e, " +
            "	Branch AS b," +
            "	Product AS p" +
            "	WHERE e.branch.id = b.id" +
            "	AND b.id = p.branch.id" +
            " GROUP BY e.id " +
            "	ORDER BY concat(e.firstName, e.lastName) ASC, length(e.position) DESC")
    List<Employee> getProductiveEmployees();
}
