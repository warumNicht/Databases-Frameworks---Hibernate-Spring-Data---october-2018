package pb2_carsDealer.repositories;

import pb2_carsDealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    List<Customer> OrderByBirthDateAscIsYoungDriver();

    @Query(value = "SELECT * FROM  Customers AS c " +
            "JOIN Sales AS s ON c.id=s.customer_id GROUP BY c.id ORDER BY COUNT(s.id) DESC, c.id ",
    nativeQuery = true)
    List<Customer> getCustomersByStatistics();

}
