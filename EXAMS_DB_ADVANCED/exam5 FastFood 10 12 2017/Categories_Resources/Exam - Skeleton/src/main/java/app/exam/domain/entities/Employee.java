package app.exam.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "position_id"
            ,referencedColumnName = "id",nullable = false)
    private Position position;

    @OneToMany(mappedBy = "employee")
    private Set<Order> orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
