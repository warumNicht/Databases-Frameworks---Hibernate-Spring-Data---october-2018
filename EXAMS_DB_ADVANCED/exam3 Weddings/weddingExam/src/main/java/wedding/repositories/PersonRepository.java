package wedding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wedding.domain.entities.Person;

import java.util.Optional;
@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    @Query(value = "SELECT p FROM Person p WHERE CONCAT(p.firstName,' ',p.middleInitial, ' ',p.lastName)=:fullName ")
    Optional<Person> findByFullName(@Param(value = "fullName") String fullName);
}
