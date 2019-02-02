package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    Optional<Car> findById(Integer id);
}
