package gamestore.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @Column(name = "title",nullable = false,unique = true)
    @Size(min = 3,max = 100,message = "Title must have between 3 and 100 symbols!")
    @Pattern(regexp = "^[A-Z].*$",message = "Title should begin with uppercase letter!")
    private String title;

    @Column(name = "price",nullable = false)
    @Digits(integer = 5,fraction = 2)
    private BigDecimal price;

    @Column(name = "size",nullable = false)
    @Digits(integer = 5,fraction = 1)
    private Double size;

    @Column(name = "trailer")
    @Size(min = 11,max = 11)
    private String trailer;

    @Column(name = "image_url")
    @Pattern(regexp = "^(http:\\/\\/|https:\\/\\/).*$",message = "Invalid URL !")
    private String thumbnailUrl;

    @Size(min = 20)
    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToMany(mappedBy = "games")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "games_orders",
    joinColumns = @JoinColumn(name = "game_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "order_id",referencedColumnName = "id"))
    private Set<Order> orders;

    public Game() {
        this.users=new HashSet<>();
        this.orders=new HashSet<>();
    }


    public Game(String title, BigDecimal price, Double size, String trailer, String thumbnailUrl, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.releaseDate = releaseDate;
        this.users=new HashSet<>();
        this.orders=new HashSet<>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("Price must be positive!");
        }
        this.price = price;
    }


    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description,price,releaseDate,size,thumbnailUrl,trailer);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        Game that=(Game)obj;
        return this.getId().equals(that.getId());
    }
}
