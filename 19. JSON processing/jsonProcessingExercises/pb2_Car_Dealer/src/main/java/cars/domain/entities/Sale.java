package cars.domain.entities;

import javax.persistence.*;

@Entity(name = "sales")
public class Sale extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "discount")
    private Discount discount;

    @OneToOne
    @JoinColumn(name = "car_id",referencedColumnName = "id",unique = true)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    public Sale() {
    }

    public Sale(Discount discount, Car car, Customer customer) {
        this.discount = discount;
        this.car = car;
        this.customer = customer;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
