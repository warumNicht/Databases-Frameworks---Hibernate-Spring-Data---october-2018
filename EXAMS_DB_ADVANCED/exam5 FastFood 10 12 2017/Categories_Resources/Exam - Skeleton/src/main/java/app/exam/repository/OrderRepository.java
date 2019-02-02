package app.exam.repository;

import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Order;
import app.exam.domain.entities.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(value = "SELECT o FROM  Order AS o WHERE" +
            " o.employee.name=:employee AND o.orderType=:type")
    List<Order> query1(@Param(value = "employee") String employee,
                       @Param(value = "type") OrderType type);
}
