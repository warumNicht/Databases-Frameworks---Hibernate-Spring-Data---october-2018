package pb1BookShop.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pb1BookShop.models.AgeRestriction;
import pb1BookShop.models.Book;
import pb1BookShop.models.EditionType;
import pb1BookShop.models.ReducedBook;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
    Iterable<Book> getAllByReleaseDateAfter(Date date);
    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN 'true' ELSE 'false' END FROM Book b  WHERE b.title= ?1 AND b.author.lastName= ?2")

    boolean existsBookByTitleAndAuthor_LastName(String title, String authorLastName);

    Iterable<Book> getAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    List<Book> getAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getAllByEditionTypeAndCopiesLessThan(EditionType editionType,int copiesCount);

    List<Book> getAllByPriceIsLessThanOrPriceGreaterThan(BigDecimal price, BigDecimal price2);

    @Query(value = "SELECT b FROM Book  b WHERE YEAR(b.releaseDate) !=?1")
    List<Book> getAllByReleaseDateDifferentFrom(int year);

    List<Book> getAllByReleaseDateLessThan(Date releaseDate);

    List<Book> getAllByTitleContaining(String title);

    @Query("SELECT b FROM Book b WHERE b.author.lastName LIKE CONCAT(?1,'%') ")
    List<Book> getAllByAuthorLastName(String start);

    @Query("SELECT COUNT(b) FROM Book b WHERE char_length(b.title) > ?1")
    long countByTitleWithLengthMoreThen(long length);

    @Query(value = "SELECT b.title,b.editionType,b.ageRestriction,b.price FROM Book b WHERE  b.title=?1")
    Object getBookInformation(String bookTitle);

    @Query(value = "SELECT new pb1BookShop.models.ReducedBookImpl(b.title,b.ageRestriction,b.editionType,b.price)" +
            " FROM Book b WHERE  b.title=?1")
    ReducedBook getReducedBookInfo(String bookTitle);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Book b SET b.copies=b.copies+ ?1 WHERE b.releaseDate> ?2")
    int increaseBooksCopies(int increment, Date date );

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Book WHERE copies<?1")
    int deleteBookWithCopiesLowerThen(int copies);

    @Transactional
    @Procedure(procedureName = "udp_get_book_count_by_author",outputParameterName = "result")
    Integer getCountOfBooksByAuthor(@Param(value = "first_name") String firstName, @Param(value = "last_name")String lastName);

}
