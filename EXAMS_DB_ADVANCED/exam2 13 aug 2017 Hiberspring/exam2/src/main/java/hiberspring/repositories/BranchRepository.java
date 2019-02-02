package hiberspring.repositories;

import hiberspring.domain.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {

    Optional<Branch> findByName(String name);

    @Query(value = "select b.id, b.name, b.town,SUM(p.clients) AS con " +
            "FROM Branch AS b " +
            "LEFT JOIN b.products AS p " +
            "GROUP BY b.id " +
            "ORDER BY  con DESC ")
    List<Object[]> getBranchStatistics2();

    @Query(value = "SELECT b.name AS br_name,t.name AS town_name, IFNULL(SUM(p.clients),0) AS con\n" +
            "FROM branches AS b\n" +
            "LEFT JOIN products AS p ON b.id=p.branch_id\n" +
            "JOIN towns AS t ON t.id=b.town_id\n" +
            "GROUP BY b.id\n" +
            "ORDER BY con DESC" ,nativeQuery = true)
    List<Object[]> getBranchStatistics();

    @Query(value = "SELECT b.name, t.name, sum(p.clients) AS clientsNum" +
            "	FROM Product AS p " +
            "	RIGHT JOIN p.branch AS b " +
            "  	INNER JOIN b.town AS t" +
            "	GROUP BY b, t" +
            "	ORDER BY clientsNum DESC")
    List<Object> getTopBranches(); //avtorsko re6enie, no neverno!!!
}
