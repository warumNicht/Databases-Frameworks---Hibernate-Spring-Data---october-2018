package app.exam.repository;

import app.exam.domain.entities.Item;
import app.exam.domain.entities.Order;
import app.exam.domain.entities.OrderItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<Order,Item> {

}
