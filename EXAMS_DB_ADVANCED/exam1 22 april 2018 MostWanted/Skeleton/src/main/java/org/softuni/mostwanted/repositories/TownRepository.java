package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town,Integer> {

    boolean existsByName(String name);

    Optional<Town> findByName(String name);

    @Query(value = "SELECT t FROM org.softuni.mostwanted.domain.entities.Town t JOIN t.racers AS r " +
            "GROUP BY  t.id " +
            "ORDER BY COUNT(r) DESC, t.name")
    List<Town> racingTowns();
}
