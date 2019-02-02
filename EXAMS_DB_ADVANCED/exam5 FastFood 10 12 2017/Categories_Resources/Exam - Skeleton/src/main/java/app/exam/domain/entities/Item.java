package app.exam.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item  extends BaseEntity implements Serializable {
    @Column(nullable = false,unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id"
            ,referencedColumnName = "id",nullable = false)
    private Category category;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "item",targetEntity = OrderItem.class)
    private Set<OrderItem> orderItems;

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

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name,price);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        Item that = (Item) obj;
        return that.getId().equals(this.getId());
    }
}
