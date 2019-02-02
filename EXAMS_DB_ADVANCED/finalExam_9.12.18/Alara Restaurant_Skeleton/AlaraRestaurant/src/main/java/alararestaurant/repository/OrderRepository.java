package alararestaurant.repository;

import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT SUM(oi.quantity) FROM Order AS c " +
            "JOIN c.orderItems AS oi " +
            " WHERE c.id=:orderId AND oi.item.id=:itemId "+
            "GROUP BY c.id ")
    Long  getItemsQuantityByOrder(@Param(value = "orderId") Integer orderId,
                                  @Param(value = "itemId") Integer itemId);
}
