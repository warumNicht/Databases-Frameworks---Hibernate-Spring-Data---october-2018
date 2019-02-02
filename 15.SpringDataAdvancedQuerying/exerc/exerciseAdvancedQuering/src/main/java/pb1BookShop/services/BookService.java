package pb1BookShop.services;

import pb1BookShop.models.Book;
import pb1BookShop.repositories.BookRepository;

import java.util.Date;

public interface BookService {
    void register(Book book);

    Iterable<Book> getAllByReleaseDateAfter(Date date);

    Iterable<Book> getAllByAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    BookRepository getBookRepository();


}
