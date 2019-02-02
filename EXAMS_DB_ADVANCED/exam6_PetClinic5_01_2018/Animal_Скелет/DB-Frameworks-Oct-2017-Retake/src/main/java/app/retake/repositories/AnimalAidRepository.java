package app.retake.repositories;

import app.retake.domain.models.AnimalAid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AnimalAidRepository extends JpaRepository<AnimalAid,Integer> {

    boolean existsByNameAndPrice(String name, BigDecimal price);

    boolean existsByName(String name);

    List<AnimalAid> getByName(String name);
}
