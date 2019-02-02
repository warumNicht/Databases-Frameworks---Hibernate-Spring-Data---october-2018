package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.domain.entities.District;
import org.softuni.mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
    @Query(value = "SELECT CASE WHEN COUNT(d) > 0 THEN 'true' ELSE 'false' END " +
            " FROM District d WHERE d.name =?1 AND d.town=?2")
    boolean existsByNameAndTown( String name,Town town);

    @Query(value = "SELECT d " +
            " FROM District d WHERE d.name=?1")
    List<District> existsByNameAndTown2( String name);

    List<District> findByName(String name);
}
