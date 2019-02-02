package alararestaurant.domain.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order  extends BaseEntity{
    @Column(nullable = false,columnDefinition = " TEXT ")
    private String customer;

    @Column(name = "date_time",nullable = false)
    private Date dateTime;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private OrderType type;

    @ManyToOne
    @JoinColumn(name = "employee_id",
    referencedColumnName = "id",nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
