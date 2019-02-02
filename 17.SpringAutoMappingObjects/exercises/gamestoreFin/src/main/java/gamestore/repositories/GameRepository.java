package gamestore.repositories;


import gamestore.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game,String> {
    Optional<Game> getByTitle(String title);

    Optional<Game> findById(String id);

    List<Game> findAll();

    Set<Game> findAllByTitleIn(Set<String> titles);
}
