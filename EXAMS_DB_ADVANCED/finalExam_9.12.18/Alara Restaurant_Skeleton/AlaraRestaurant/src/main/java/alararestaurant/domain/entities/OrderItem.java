package alararestaurant.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "order_id",
    referencedColumnName = "id",nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id",
            referencedColumnName = "id",nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer quantity;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
