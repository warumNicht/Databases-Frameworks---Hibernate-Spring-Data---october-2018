package app.exam.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{
    @Column(nullable = false)
    private String customer;

    @Column(nullable = false)
    private Date date;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "order_type")
    private OrderType orderType;

    @ManyToOne
    @JoinColumn(name = "employee_id",
            referencedColumnName = "id",nullable = false)
    private Employee employee;

    @Transient
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<OrderItem> orderItems;


    public void setTotalPrice(){
        BigDecimal res=new BigDecimal(0);
        for (OrderItem orderItem : this.orderItems) {
            BigDecimal quant=new BigDecimal(orderItem.getQuantity());
            BigDecimal price = orderItem.getItem().getPrice();
            res=res.add(price.multiply(quant));
        }
        this.totalPrice=res;
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }



    @Override
    public int hashCode() {
        return Objects.hash(customer,date);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        Order that = (Order) obj;
        return that.getId().equals(this.getId());
    }
}
