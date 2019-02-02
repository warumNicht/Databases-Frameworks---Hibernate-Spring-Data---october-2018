package pb1BookShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import pb1BookShop.models.Author;
import pb1BookShop.models.Book;
import pb1BookShop.services.AuthorService;
import pb1BookShop.services.BookService;
import pb1BookShop.services.CategoryService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BookShopController implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
@Autowired
    public BookShopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
    this.categoryService = categoryService;
    this.bookService = bookService;
}

    @Override
    public void run(String... strings) throws Exception {
        this.authorService.seedAuthors();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();

//       this.getAllBooksAfterYear2000Variant1(); //the date is printed for better comparision
//       this.getAllBooksAfterYear2000Variant2(2000);
//
//      this.getAuthorsWithBookBefore(1990);
//
//       this.printAuthorsByCountOfBooksDesc();
//
//        this.getBooksByAuthorOrderedByReleaseDateDescTitleAsc("George","Powell");
    }


    /**
     *  1.	Get all books after the year 2000 variant 1
     * @throws ParseException
     */

    private void getAllBooksAfterYear2000Variant1() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date releaseDate = formatter.parse("31/12/2000");

        Iterable<Book> allByReleaseDateAfter = this.bookService.getAllByReleaseDateAfter(releaseDate);
        for (Book book : allByReleaseDateAfter) {
            System.out.printf("%-40s %s%n",
                    book.getTitle(),book.getReleaseDate());
        }
    }
    /**
     *  1.	Get all books after the year 2000 variant 2
     *
     */
    private void getAllBooksAfterYear2000Variant2(int year) {
        List<Book> allByReleaseDateGreaterThanYear = this.bookService.getAllByReleaseDateGreaterThanYear(year);
        for (Book book : allByReleaseDateGreaterThanYear) {
            System.out.printf("%-40s %s%n",
                    book.getTitle(),book.getReleaseDate());
        }
    }
    /**
     *  2.	Get all authors with at least one book with release date before 1990.
     *  Print their first name and last name.
     *
     */
    private void getAuthorsWithBookBefore(int year) {
        Iterable<Author> authorsWithAtLeastOneBookBeforeYear = this.authorService.getAuthorsWithAtLeastOneBookBeforeYear(year);
        for (Author author : authorsWithAtLeastOneBookBeforeYear) {
            System.out.printf("%-25s %s%n",author.getFirstName(),author.getLastName());
        }
    }

    /**
     *  3.	Get all authors, ordered by the number of their books (descending).
     *  Print their first name, last name and book count.
     */
    private void printAuthorsByCountOfBooksDesc() {
        Iterable<Author> authorsByOrderByBooksDesc = this.authorService.findAuthorsByCountOfBooksDesc();

        for (Author author : authorsByOrderByBooksDesc) {
            System.out.printf("%-10s %-10s %d%n",author.getFirstName(),author.getLastName(),author.getBooks().size());
        }
    }
    /**
     * 4.	Get all books from author George Powell, ordered by their release date (descending),
     * then by book title (ascending). Print the book's title, release date and copies.
     */
    private void getBooksByAuthorOrderedByReleaseDateDescTitleAsc(String firstName, String lastName) {
        Iterable<Book> george_powell = this.bookService
                .getAllByAuthor_LastNameOrderByReleaseDateDescTitleAsc(firstName,lastName);

        for (Book book : george_powell) {
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MMM-dd");
            String dateString=formatter2.format(book.getReleaseDate());
            System.out.printf("%-30s %s    copies: %d%n",book.getTitle(), dateString,book.getCopies());
        }
    }
}
