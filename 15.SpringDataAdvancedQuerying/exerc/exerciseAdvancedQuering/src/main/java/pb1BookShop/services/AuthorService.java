package pb1BookShop.services;
import pb1BookShop.models.Author;
import pb1BookShop.repositories.AuthorRepository;

import java.util.Date;

public interface AuthorService {
    void register(Author author);
    long size();
    Author getAuthorById(long id);

    Iterable<Author> getAuthorsByBooksBefore(Date date);

    Iterable<Author> findAuthorsByOrderByBooksDesc();

    Iterable<Author> getAuthorsWithAtLeastOneBookBefore(Date date);

    AuthorRepository getAuthorRepository();
}
