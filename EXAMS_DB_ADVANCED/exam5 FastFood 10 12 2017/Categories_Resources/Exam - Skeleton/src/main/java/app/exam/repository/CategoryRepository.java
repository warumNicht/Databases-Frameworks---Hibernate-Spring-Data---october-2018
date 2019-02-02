package app.exam.repository;

import app.exam.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    boolean existsByName(String name);

    Category findByName(String name);


    @Query(value = "SELECT c.name, i.name, i.price*SUM(oi.quantity) AS suma,SUM(oi.quantity) AS broi " +
            " FROM Category c " +
            "JOIN c.items AS i " +
            "JOIN i.orderItems AS oi "+
            "WHERE c.name IN :names " +
            "GROUP BY c.id,i.id " +
            "ORDER BY suma DESC , broi DESC"
            )
    List<Object[]> catStatistics(@Param(value = "names") List<String> names);

    @Query(value = "SELECT res.name, res.suma ,res.con\n" +
            "FROM categories AS c2\n" +
            "JOIN (SELECT c.name AS name, i.price*SUM(oi.quantity) AS suma, SUM(oi.quantity) AS con\n" +
            "FROM categories AS c\n" +
            "JOIN items AS i ON c.id=i.category_id\n" +
            "JOIN order_items AS oi ON oi.item_id=i.id\n" +
            "GROUP BY c.id,i.id\n" +
            "ORDER BY  suma DESC, con DESC ) AS res\n" +
            "ON c2.name =res.name\n" +
            "GROUP BY c2.id",nativeQuery = true
    )
    List<Object[]> catStatisticsNative();




}
