package pb1BookShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import pb1BookShop.models.*;
import pb1BookShop.services.AuthorServiceImpl;
import pb1BookShop.services.BookServiceImpl;
import pb1BookShop.services.CategoryServiceImpl;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private BookServiceImpl bookService;
    private AuthorServiceImpl authorService;
    private CategoryServiceImpl categoryService;

    @Autowired
    public ConsoleRunner(BookServiceImpl bookService, AuthorServiceImpl authorService, CategoryServiceImpl categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String restrictionType=reader.readLine();
        this.getAllBooksByRestrictionType(restrictionType); //pb 1
//        this.getAllBooksByGoldenEditionType(); // pb2

//        this.printBooksByPrice(); // pb 3
//        this.printBooksNotInYear(1998); // pb 4

//        String dateStr = reader.readLine();  //pb 5
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        Date date = format.parse(dateStr);
//        this.printBooksBeforeDate(date);

//        String firstNameSuffix = reader.readLine();  //pb 6
//        this.printAuthorsWithFirstNameEndingWith(firstNameSuffix);

//        this.printBooksContainigString("WOR"); //pb 7

//        this.printBooksWithsAuthorsLastnameStarting("R"); //pb 8

//        this.printCountOfTitlesWithAutors(12L); //pb 9

 //       this.printAuthorsBySumOfCopiesOfTheirBooks(); //pb 10

//        this.printInfoForBookByTitle("A Many-Splendoured Thing"); //pb 11 variant 1

//        this.printReducedBook("Absalom "); //pb 11 variant 2

        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy"); //pb 12

        String dateString = reader.readLine();
        Date   date       = format.parse ( dateString );
        int copies=Integer.parseInt(reader.readLine());
        this.printCountOfIncreasedBookCopiesAfter(copies,date);

//        int copies=Integer.parseInt(reader.readLine());  //pb 13
//        this.printCountOfDeletedBooksWithCopiesLess(copies);

//        String[] names=reader.readLine().split("\\s+");
 //       this.printWrittenBooksByAuthor(names[0],names[1]);


//        this.seedFileLine("D:\\5. Java DB Advanced\\8. Spring intro\\EXERCISES\\SeedData\\authors.txt","author");
//        this.seedFileLine("D:\\5. Java DB Advanced\\8. Spring intro\\EXERCISES\\SeedData\\categories.txt","category");
//        this.seedFileLine("D:\\5. Java DB Advanced\\8. Spring intro\\EXERCISES\\SeedData\\books.txt","book");
    }

    /**
     * Problem 1.	Books Titles by Age Restriction
     */
    private void getAllBooksByRestrictionType(String restrictionType) {
        List<Book> allByAgeRestriction = this.bookService.getBookRepository()
                .getAllByAgeRestriction(AgeRestriction.valueOf(restrictionType.toUpperCase()));
        for (Book book : allByAgeRestriction) {
            System.out.println(book.getTitle());
        }
    }

    /**
     * 2.	Golden Books
     */
    private void getAllBooksByGoldenEditionType() {
        List<Book> allByEditionTypeAndCopiesLessThan = this.bookService.getBookRepository()
                .getAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000);
        for (Book book : allByEditionTypeAndCopiesLessThan) {
            System.out.println(book.getTitle());
        }
    }

    /**
     * 3.	Books by Price
     */
    private void printBooksByPrice() {
        List<Book> allByPriceIsLessThanAndPriceGreaterThan = this.bookService.getBookRepository()
                .getAllByPriceIsLessThanOrPriceGreaterThan(BigDecimal.valueOf(5.0), BigDecimal.valueOf(40.0));
        for (Book book : allByPriceIsLessThanAndPriceGreaterThan) {
            System.out.println(String.format("%s - $%.2f", book.getTitle(), book.getPrice()));
        }
    }

    /**
     * 4.	Not Released Books
     */
    private void printBooksNotInYear(int year) {
        List<Book> allByReleaseDateDifferentFrom = this.bookService
                .getBookRepository().getAllByReleaseDateDifferentFrom(year);
        for (Book book : allByReleaseDateDifferentFrom) {
            System.out.println(book.getTitle());
        }
    }

    /**
     * 5.	Books Released Before Date
     */
    private void printBooksBeforeDate(Date date) {
        List<Book> allByReleaseDateLessThan = this.bookService.getBookRepository().getAllByReleaseDateLessThan(date);
        for (Book book : allByReleaseDateLessThan) {
            System.out.println(book.getTitle());
        }
    }

    /**
     *  6.	Authors Search
     */
    private void printAuthorsWithFirstNameEndingWith(String suffix){
        List<Author> allByFirstNameEndsWith = this.authorService.getAuthorRepository().getAllByFirstNameEndsWith(suffix);
        for (Author author : allByFirstNameEndsWith) {
            System.out.printf("%s %s%n",author.getFirstName(),author.getLastName());

        }
    }

    /**
     *  7.	Books Search
     * @param title
     */
    private void printBooksContainigString(String title){
        List<Book> allByTitleContaining = this.bookService.getBookRepository().getAllByTitleContaining(title);
        for (Book book : allByTitleContaining) {
            System.out.println(book.getTitle());
        }
    }

    /**
     *   8.	Book Titles Search
     * @param lastname
     */
    private void printBooksWithsAuthorsLastnameStarting(String lastname){
        List<Book> allByAuthorLastName = this.bookService.getBookRepository().getAllByAuthorLastName(lastname);
        for (Book book : allByAuthorLastName) {
            System.out.printf("%s (%s %s)%n",
                    book.getTitle(),book.getAuthor().getFirstName(),book.getAuthor().getLastName());
        }
    }

    /**
     * 9.	Count Books
     * @param number
     */
    private void printCountOfTitlesWithAutors(Long number){
        long countTitlesWithLengthMoreThen = this.bookService
                .getBookRepository().countByTitleWithLengthMoreThen(number);
        System.out.println(countTitlesWithLengthMoreThen);
    }

    /**
     *  10.	Total Book Copies
     */
    private void printAuthorsBySumOfCopiesOfTheirBooks(){
        List<Object[]> resultBySumOfCopies = this.authorService.getAuthorRepository().getBySumBooks();
        for (Object[] arrayResult: resultBySumOfCopies) {
            System.out.printf("%s %s - %d%n",arrayResult[0],arrayResult[1],(Long)arrayResult[2]);
        }
    }
    /**
     *  11.	Reduced Book solution 1
     */
    private void printInfoForBookByTitle(String bookTitle){
        Object bookFields = this.bookService.getBookRepository().getBookInformation(bookTitle);
        Object[]res=(Object[])bookFields;
        if(res!=null){
            System.out.printf("%s %s %s %.2f%n",res[0],((EditionType)res[1]).name(),
                    ((AgeRestriction)res[2]).name(), (BigDecimal)res[3]);
        }
    }
    /**
     *  11.	Reduced solution 2
     */
    private void printReducedBook(String bookTitle){
        ReducedBook bookInfo = this.bookService.getBookRepository().getReducedBookInfo(bookTitle);
        if(bookInfo!=null){
            System.out.printf("%s %s %s %.2f%n",bookInfo.getTitle(),bookInfo.getEditionType().name(),
                    bookInfo.getAgeRestriction().name(),bookInfo.getPrice());
        }
    }
    /**
     *  12.	* Increase Book Copies
     */
    private void printCountOfIncreasedBookCopiesAfter(int copies,Date date){
        int increaseBooksCopies = this.bookService.getBookRepository().increaseBooksCopies(copies, date);
        System.out.println(increaseBooksCopies*copies);
    }
    /**
     *  13.	* Remove Books
     */
    private void printCountOfDeletedBooksWithCopiesLess(int copies){
        int booksWithCopiesLowerThen = this.bookService.getBookRepository().deleteBookWithCopiesLowerThen(copies);
        System.out.printf("%d books were deleted%n",booksWithCopiesLowerThen);
    }
    /**
     *  14.	* Stored Procedure
     *
     *  DROP PROCEDURE IF EXISTS udp_get_book_count_by_author;
     * DELIMITER $$
     * CREATE PROCEDURE udp_get_book_count_by_author(first_name VARCHAR(255)
     * ,last_name VARCHAR(255),OUT result  INT(11))
     * BEGIN
     *
     * SET result := (SELECT COUNT(b.id)
     * FROM authors AS a
     * LEFT JOIN books AS b ON a.id=b.author_id
     * WHERE a.first_name=first_name AND a.last_name=last_name
     * GROUP BY a.id);
     *
     * END $$
     */
    private void printWrittenBooksByAuthor(String firstName,String lastName){
        Integer countOfBooksByAuthor = this.bookService.getBookRepository()
                .getCountOfBooksByAuthor(firstName, lastName);
        if(countOfBooksByAuthor==null){
            System.out.printf("Author %s %s does not exists in the database%n",firstName,lastName);
            return;
        }
        if(countOfBooksByAuthor==0){
            System.out.printf("%s %s has not written any books yet%n",firstName,lastName);
        }else {
            System.out.printf("%s %s has written %d book%s%n",firstName,lastName,
                    countOfBooksByAuthor, countOfBooksByAuthor>1 ? "s" :"");
        }
    }


    private void seedFileLine(String filePath, String entity) throws IOException, ParseException {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine();

        while (line != null) {
            switch (entity) {
                case "author": {
                    this.seedAuthor(line);
                }
                break;
                case "category": {
                    this.seedCategory(line);
                }
                break;
                case "book": {
                    this.seedBook(line.trim());
                }
                break;
            }


            line = reader.readLine();
        }
    }

    private void seedBook(String line) throws ParseException {
        line = line.trim();

        String[] tockens = line.split("\\s+");
        Random random = new Random();
        int authorsSize = (int) this.authorService.size();
        long randomAuthorId = random.nextInt(authorsSize) + 1;


        Author randomAuthor = this.authorService.getAuthorById(randomAuthorId);
        char[] chars = tockens[0].toCharArray();
        String fineNumber = this.cleanseUnwantedChars(chars);
        int indexTypes = Integer.valueOf(fineNumber);
        EditionType editionType = EditionType.values()[indexTypes];

        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date releaseDate = formatter.parse(tockens[1]);

        int copies = Integer.parseInt(tockens[2]);

        BigDecimal price = new BigDecimal(tockens[3]);

        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tockens[4])];

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

        int countOfCategories = this.categoryService.countAll();

        int randomCatId = random.nextInt(countOfCategories) + 1;
        Category randomCategory = this.categoryService.getCategoryById(randomCatId);

        book.getCategories().add(randomCategory);

        this.bookService.register(book);

    }

    private String cleanseUnwantedChars(char[] chars) {
        StringBuilder res = new StringBuilder();
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                res.append(aChar);
            }
        }
        return res.toString();
    }

    private void seedCategory(String line) {
        if (line.equals("")) {
            return;
        }
        Category category = new Category(line);
        this.categoryService.register(category);
    }

    private void seedAuthor(String line) {
        String[] tockens = line.split("\\s+");
        Author author = new Author(tockens[0], tockens[1]);
        this.authorService.register(author);
    }


}
