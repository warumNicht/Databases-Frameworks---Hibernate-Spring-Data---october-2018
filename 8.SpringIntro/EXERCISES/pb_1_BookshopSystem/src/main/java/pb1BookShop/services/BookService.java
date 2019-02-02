package pb1BookShop.services;

import pb1BookShop.models.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException, ParseException;

    void register(Book book);

    Iterable<Book> getAllByReleaseDateAfter(Date date);

    List<Book> getAllByReleaseDateGreaterThanYear(int year);

    Iterable<Book> getAllByAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);
}
