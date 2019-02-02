package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entities.Race;
import org.softuni.mostwanted.domain.entities.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry,Integer> {
    Optional<RaceEntry> findById(Integer id);
}
