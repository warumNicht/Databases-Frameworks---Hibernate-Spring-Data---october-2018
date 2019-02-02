package pb2_carsDealer.repositories;

import pb2_carsDealer.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {

    @Query(value = "SELECT s FROM Supplier s LEFT JOIN s.parts AS p " +
            "WHERE s.isImporter=false " +
            "GROUP BY  s.id ORDER BY COUNT (p) DESC ")
    List<Supplier> getAllNotImportingParts();
}
