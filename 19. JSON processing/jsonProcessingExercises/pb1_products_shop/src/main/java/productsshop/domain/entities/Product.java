package productsshop.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "seller_id",referencedColumnName = "id",nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "buyer_id",referencedColumnName = "id")
    private User buyer;

 //   @ManyToMany(mappedBy = "products")
    @ManyToMany()
    @JoinTable(name = "category_products",
    joinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;

    public Product() {
        this.categories=new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
