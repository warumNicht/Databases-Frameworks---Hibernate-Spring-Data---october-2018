package pb1BookShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb1BookShop.models.*;
import pb1BookShop.repositories.BookRepository;
import pb1BookShop.util.FileUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Primary
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private final String BOOKS_PATH="src\\main\\resources\\seedData\\books.txt";
    private final FileUtil fileUtil;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException, ParseException {
        if(this.bookRepository.count()==0){
            String[] fileContent = this.fileUtil.getFileContent(BOOKS_PATH);
            for (String currentBook : fileContent) {

                this.readAndRegisterBook(currentBook);
            }
        }
    }

    @Transactional
    @Override
    public void register(Book book) {
        if(this.bookRepository
                .existsBookByTitleAndAuthor_LastName(book.getTitle(),book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName(),book.getReleaseDate())){
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
    public List<Book> getAllByReleaseDateGreaterThanYear(int year) {
        return this.bookRepository.getAllByReleaseDateGreaterThanYear(year);
    }


    @Override
    public Iterable<Book> getAllByAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName) {
        return this.bookRepository.getAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(firstName,lastName);
    }

    private void readAndRegisterBook(String line) throws ParseException {
        line=line.trim();

        String[] tockens=line.split("\\s+");
        Random random=new Random();
        int authorsSize= (int) this.authorService.size();
        long randomAuthorId=random.nextInt(authorsSize)+1;

        Author randomAuthor=this.authorService.getAuthorById(randomAuthorId);

        int indexTypes = Integer.valueOf(tockens[0]);
        EditionType editionType=EditionType.values()[indexTypes];

        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date releaseDate = formatter.parse(tockens[1]);

        int copies=Integer.parseInt(tockens[2]);

        BigDecimal price = new BigDecimal(tockens[3]);

        AgeRestriction ageRestriction=AgeRestriction.values()[Integer.parseInt(tockens[4])];

        StringBuilder titleBuilder = new StringBuilder();
        for (int i = 5; i < tockens.length; i++) {
            titleBuilder.append(tockens[i]).append(" ");
        }
        titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
        String title = titleBuilder.toString();

        Book book = new Book();
        book.setAuthor(randomAuthor);
        book.setEditionType(editionType);
        book.setReleaseDate(releaseDate);
        book.setCopies(copies);
        book.setPrice(price);
        book.setAgeRestriction(ageRestriction);
        book.setTitle(title);

        Set<Category> randomSetOfCategories = this.getRandomSetOfCategories();
        book.setCategories(randomSetOfCategories);

        this.register(book);

    }
    private Set<Category> getRandomSetOfCategories(){
        Set<Category> categories=new HashSet<>();
        Random random=new Random();

        for (int i = 0; i < random.nextInt(5)+1; i++) {
            int countOfCategories = this.categoryService.countAll();
            int randomCatId=random.nextInt(countOfCategories)+1;
            Category randomCategory=this.categoryService.getCategoryById(randomCatId);
            categories.add(randomCategory);
        }
        return categories;
    }

}
