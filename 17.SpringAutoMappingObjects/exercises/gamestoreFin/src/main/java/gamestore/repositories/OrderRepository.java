package gamestore.repositories;

import gamestore.domain.entities.Game;
import gamestore.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    @Query(value = "SELECT o FROM Order o JOIN o.products AS g WHERE g IN :game")
    Set<Order> getAllContainingGame(@Param(value = "game") Game game);
}
