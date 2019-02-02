package cars.domain.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends BaseWithName {
    @Column(name = "birth_date")
 //   @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @Column(name = "is_young_driver")
    private boolean isYoungDriver;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Sale> sales;

    public Customer() {
        super();
        this.sales=new HashSet<>();
    }

    public Customer(String name, Date birthDate, boolean isYoungDriver) {
        super(name);
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.sales=new HashSet<>();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
