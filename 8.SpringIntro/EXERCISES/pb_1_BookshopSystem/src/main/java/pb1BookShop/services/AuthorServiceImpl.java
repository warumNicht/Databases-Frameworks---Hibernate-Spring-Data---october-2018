package pb1BookShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pb1BookShop.models.Author;
import pb1BookShop.repositories.AuthorRepository;
import pb1BookShop.util.FileUtil;

import java.io.IOException;
import java.util.Date;

@Service
@Primary
public class AuthorServiceImpl implements AuthorService {
    private final String AUTHORS_PATH="src\\main\\resources\\seedData\\authors.txt";
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(this.authorRepository.count()!=0){
            return;
        }
        String[] authorsLines=this.fileUtil.getFileContent(AUTHORS_PATH);
        for (String line : authorsLines) {
            String[] tockens=line.split("\\s+");
            Author author=new Author(tockens[0],tockens[1]);
            this.register(author);
        }
    }

    @Override
    public void register(Author author) {
        if(this.authorRepository.existsAuthorByLastName(author.getFirstName(),author.getLastName())){
            throw new IllegalArgumentException(String.format("Author %s %s already exists",
                    author.getFirstName(),author.getLastName()));
        }
        this.authorRepository.save(author);
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
    public Iterable<Author> findAuthorsByCountOfBooksDesc() {
        return this.authorRepository.findAuthorsByOrderOfBooksDesc();
    }

    @Override
    public Iterable<Author> getAuthorsWithAtLeastOneBookBefore(Date date) {
        return this.authorRepository.getAuthorsWithAtLeastOneBookBefore(date);
    }

    @Override
    public Iterable<Author> getAuthorsWithAtLeastOneBookBeforeYear(int year) {
        return this.authorRepository.getAuthorsWithAtLeastOneBookBeforeYear(year);
    }

}
