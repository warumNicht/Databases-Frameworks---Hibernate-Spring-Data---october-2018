package pb1BookShop.models;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getBookCountByAuthor",
        procedureName = "udp_get_book_count_by_author",parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN,
                name = "first_name",type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN,
                name = "last_name",type = String.class),
                @StoredProcedureParameter(name = "result",
                mode = ParameterMode.OUT, type = Integer.class)
        },
        resultClasses = int.class)
})
public class Book  implements ReducedBook{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "age_restriction",nullable = false)
    private AgeRestriction ageRestriction;

    @Column(name = "copies",nullable = false)
    private int copies;

    @Column(name = "description",length = 1000)
    private String description;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "edition_type",nullable = false)
    private EditionType editionType;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "title",length = 50,nullable = false)
    private String title;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id",referencedColumnName = "id")
    private Author author;

    @ManyToMany(cascade = CascadeType.MERGE,targetEntity = Category.class)
    @JoinTable(name = "books_categories",
    joinColumns = @JoinColumn(name = "book_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"))
    private Set<Category> categories;

    public Book() {
        this.categories=new HashSet<>();
    }

    public Book(AgeRestriction ageRestriction, int copies, String description, EditionType editionType, BigDecimal price, Date releaseDate, String title, Author author) {
        this.ageRestriction = ageRestriction;
        this.copies = copies;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.releaseDate = releaseDate;
        this.title = title;
        this.author = author;
        this.categories=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
