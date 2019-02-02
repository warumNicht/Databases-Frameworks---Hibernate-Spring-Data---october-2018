package wedding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import wedding.domain.entities.Venue;

import java.util.List;

@Repository
public interface VenueRepo extends JpaRepository<Venue,Integer> {
    @Query(value = "SELECT v FROM Venue v " +
            "JOIN v.weddings AS w " +
            "WHERE v.town='Sofia'" +
            "GROUP BY v.id " +
            "HAVING COUNT(w)>=3 " +
            "ORDER BY v.capacity ASC ")
    List<Venue> venuesFromSofia();
}
