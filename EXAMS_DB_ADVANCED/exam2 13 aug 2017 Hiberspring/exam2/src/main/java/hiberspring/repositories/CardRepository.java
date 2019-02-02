package hiberspring.repositories;



import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<EmployeeCard, Integer> {

    Optional<EmployeeCard> findByNumber(String number);

    @Query(value = "SELECT * FROM cards AS c \n" +
            "LEFT JOIN employees AS e ON e.card_id=c.id \n" +
            "WHERE e.id IS NULL \n" +
            "GROUP BY c.id \n" +
            "ORDER BY c.id "
            ,nativeQuery = true)
    List<EmployeeCard> cardWithoutEmployeeNativeReturnEntities();

    @Query(value = "SELECT c.number FROM cards AS c \n" +
            "LEFT JOIN employees AS e ON e.card_id=c.id \n" +
            "WHERE e.id IS NULL \n" +
            "GROUP BY c.id \n" +
            "ORDER BY c.id "
            ,nativeQuery = true)
    List<Object> cardWithoutEmployeeReturnsObject();

    List<EmployeeCard> findByEmployeeIsNull();

    @Query(value = "SELECT c FROM EmployeeCard c " +
            "LEFT JOIN c.employee AS e " +
            "WHERE e IS NULL " +
            "GROUP BY c.id " +
            "ORDER BY c.id")
    List<EmployeeCard> cardWitoutEmployee2();
}
