package pb1BookShop.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pb1BookShop.models.Book;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    Iterable<Book> getAllByReleaseDateAfter(Date date);
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN 'true' ELSE 'false' END " +
            "FROM Book b  WHERE b.title= ?1 AND b.author.firstName= ?2 AND b.author.lastName= ?3 AND b.releaseDate= ?4")
    boolean existsBookByTitleAndAuthor_LastName(String title,String authorFirstName, String authorLastName,Date releaseDate);

    @Query("SELECT b FROM Book b WHERE YEAR(b.releaseDate) > ?1")
    List<Book> getAllByReleaseDateGreaterThanYear(int year);

    Iterable<Book> getAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    //List<Book>
}
