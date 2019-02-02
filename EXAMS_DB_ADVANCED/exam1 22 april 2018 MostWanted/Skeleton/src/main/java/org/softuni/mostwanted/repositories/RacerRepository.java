package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entities.District;
import org.softuni.mostwanted.domain.entities.Racer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RacerRepository extends JpaRepository<Racer,Integer> {
    boolean existsByName(String name);
    Optional<Racer> findByName(String name);

    @Query(value = "SELECT r FROM org.softuni.mostwanted.domain.entities.Racer r " +
            "JOIN r.cars AS c " +
            "GROUP BY r.id " +
            "ORDER BY COUNT(c) DESC, r.name")
    List<Racer> racingCars();

    @Query(value = "SELECT r FROM org.softuni.mostwanted.domain.entities.Racer r " +
            "JOIN r.entries AS e " +
            "GROUP BY r.id " +
            "ORDER BY COUNT(e) DESC, r.name")
    List<Racer> mostWanted(Pageable pageable);

    @Query(value = "SELECT * FROM racers AS r " +
            "JOIN race_entries AS e ON e.racer_id=r.id " +
            "GROUP BY r.id " +
            "ORDER BY COUNT(e.id) DESC, r.name", nativeQuery = true)
    List<Racer> mostWanted2(Pageable pageable);
}
