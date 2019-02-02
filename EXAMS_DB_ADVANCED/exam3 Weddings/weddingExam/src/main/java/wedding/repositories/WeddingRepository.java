package wedding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import wedding.domain.entities.Wedding;

import java.util.List;

@Repository
public interface WeddingRepository extends JpaRepository<Wedding,Integer> {
    @Query(value = "select w, COUNT(i) AS invited, SUM(CASE WHEN i.family='bride' THEN 1 ELSE 0 END)," +
            "SUM(CASE WHEN i.family='bridegroom' THEN 1 ELSE 0 END), SUM(CASE WHEN i.attending='true' THEN 1 ELSE 0 END) AS confirmed " +
            "FROM Wedding w JOIN w.invitations AS i " +
            "GROUP BY w.id " +
            "ORDER BY invited DESC, confirmed ASC ")
    List<Object[]> weddingStat();

    @Query(value = "SELECT SUM(CASE WHEN p.amount IS NULL  THEN 0 ELSE p.amount END ) FROM Wedding w " +
            " JOIN w.invitations AS i " +
            "JOIN i.present AS p " +
            "WHERE w.id=:weddId " +
            "GROUP BY w.id ")
    Long getCash(@Param(value = "weddId") Integer id);

    @Query(value = "SELECT SUM(CASE WHEN p.size='NotSpecified'   THEN 0 ELSE 1 END ) FROM Wedding w " +
            " JOIN w.invitations AS i " +
            "JOIN i.present AS p " +
            "WHERE w.id=:weddId " +
            "GROUP BY w.id ")
    Long getGiftCount(@Param(value = "weddId") Integer id);
}
