package pb1BookShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pb1BookShop.models.Author;
import pb1BookShop.repositories.AuthorRepository;

import java.util.Date;

@Service
@Primary
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public void register(Author author) {
        if(this.authorRepository.existsAuthorByLastName(author.getFirstName(),author.getLastName())){
            throw new IllegalArgumentException(String.format("Author %s %s already exists",
                    author.getFirstName(),author.getLastName()));
        }
        this.authorRepository.save(author);
    }

    public AuthorRepository getAuthorRepository() {
        return authorRepository;
    }

    @Override
    public long size() {
        return this.authorRepository.countByIdAfter(0);
    }

    @Override
    public Author getAuthorById(long id) {
        return this.authorRepository.getAuthorById(id);
    }

    @Override
    public Iterable<Author> getAuthorsByBooksBefore(Date date) {
        return null;
    }
    @Override
    public Iterable<Author> findAuthorsByOrderByBooksDesc() {
        return this.authorRepository.findAuthorsByOrderByBooksDesc();
    }

    @Override
    public Iterable<Author> getAuthorsWithAtLeastOneBookBefore(Date date) {
        return this.authorRepository.getAuthorsWithAtLeastOneBookBefore(date);
    }

}
