package photography.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import photography.domain.entities.Photographer;

import java.util.List;

public interface PhotographerRepo extends JpaRepository<Photographer,Integer> {
    @Query(value = "SELECT p FROM Photographer p WHERE CONCAT(p.firstName, ' ',p.lastName)=:fullName ")
    List<Photographer> getByFullName(@Param(value = "fullName") String fullName);

    List<Photographer> findAllByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT p FROM Photographer p " +
            "ORDER BY p.firstName, p.lastName DESC ")
    List<Photographer> query1();

    @Query(value = "SELECT *\n" +
            "FROM photographers AS p\n" +
            "JOIN `dslr_cameras` AS c ON p.primary_camera_id=c.id " +
            "JOIN photographers_lenses AS pl ON pl.photographer_id=p.id \n" +
            "JOIN lenses AS l ON l.id=pl.lens_id AND l.focal_length<=30\n" +
            "GROUP BY p.id\n" +
            "ORDER BY p.first_name;",nativeQuery = true)
    List<Photographer> query2();

    @Query(value = "SELECT p FROM Photographer  p " +
            "JOIN p.lenses AS le " +
            "WHERE p.primaryCamera.make=p.secondaryCamera.make " +
            "GROUP BY  p.id")
    List<Photographer> query3();

}
