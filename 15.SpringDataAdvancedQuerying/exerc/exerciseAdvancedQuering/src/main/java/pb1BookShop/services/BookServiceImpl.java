package pb1BookShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb1BookShop.models.Book;
import pb1BookShop.repositories.BookRepository;

import java.util.Date;

@Service

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    @Transactional
    @Override
    public void register(Book book) {
        if(this.bookRepository.existsBookByTitleAndAuthor_LastName(book.getTitle(),book.getAuthor().getLastName())){
            throw new IllegalArgumentException(String.format("Book with title %s and author %s already exists",
                    book.getTitle(), book.getAuthor().getLastName()));
        }
        this.bookRepository.save(book);
    }

    @Override
    public Iterable<Book> getAllByReleaseDateAfter(Date date) {
        return this.bookRepository.getAllByReleaseDateAfter(date);
    }


    @Override
    public Iterable<Book> getAllByAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName) {
        return this.bookRepository.getAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(firstName,lastName);
    }
}
