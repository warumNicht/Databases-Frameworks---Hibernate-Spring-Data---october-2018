package pb1BookShop.services;
import pb1BookShop.models.Author;

import java.io.IOException;
import java.util.Date;

public interface AuthorService {
    void seedAuthors() throws IOException;

    void register(Author author);
    long size();
    Author getAuthorById(long id);

    Iterable<Author> getAuthorsByBooksBefore(Date date);

    Iterable<Author> findAuthorsByCountOfBooksDesc();

    Iterable<Author> getAuthorsWithAtLeastOneBookBefore(Date date);

    Iterable<Author> getAuthorsWithAtLeastOneBookBeforeYear(int year);
}
