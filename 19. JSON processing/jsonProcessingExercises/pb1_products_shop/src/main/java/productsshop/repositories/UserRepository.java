package productsshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productsshop.domain.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "SELECT u FROM User u " +
            "JOIN u.soldProducts AS p " +
            "WHERE p.buyer IS NOT NULL GROUP BY u.id ORDER BY u.lastName, u.firstName")
    List<User> getAllWithAtLeastOneSoldProduct();

    @Query(value = "SELECT u FROM User u " +
            "JOIN u.soldProducts AS p " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY COUNT(p) DESC")
    List<User> getAllWithSoldProduct();
}
