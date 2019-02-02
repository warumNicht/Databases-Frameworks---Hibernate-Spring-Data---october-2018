package mostwanted.domain.entities;

import org.hibernate.annotations.SortComparator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "racers")
public class Racer  extends BaseEntity{
    @Column(nullable = false,unique = true)
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal bounty;

    @ManyToOne
    @JoinColumn(name = "town_id",referencedColumnName = "id")
    private Town homeTown;

    @OneToMany(mappedBy = "racer")
    @SortComparator(value = Car.CarComparator.class)
    private Set<Car> cars;

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

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
