package gamestore.repositories;


import gamestore.domain.entities.Game;
import gamestore.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT CASE WHEN COUNT(g)>0 THEN 'true' ELSE 'false' END " +
            "FROM User u JOIN u.games AS g WHERE g.title =:title AND u.email=:email")
    boolean hasUserGame(@Param(value = "title") String title,@Param(value = "email") String email);

    @Query(value = "SELECT u FROM User u JOIN u.games AS g WHERE g IN :game")
    Set<User> getAllContainingGame(@Param(value = "game") Game game);
}
