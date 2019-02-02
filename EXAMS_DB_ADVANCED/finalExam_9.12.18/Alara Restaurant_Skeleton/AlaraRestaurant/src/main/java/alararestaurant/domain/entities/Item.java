package alararestaurant.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "items")
public class Item  extends BaseEntity{
    @Column(nullable = false,unique = true,length = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id",
    nullable = false)
    private Category category;

    @Column
    private BigDecimal price;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
