package app.retake.repositories;

import app.retake.domain.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Integer> {

    boolean existsByPassportSerialNumber(String passport_serialNumber);

    Animal findByPassportSerialNumber(String number);

    @Query(value = "SELECT a FROM Animal  a " +
            "WHERE a.passport.ownerPhoneNumber =:number " +
            "ORDER BY a.age, a.passport.serialNumber ")
    List<Animal> query1(@Param(value = "number") String nubmer);
}
