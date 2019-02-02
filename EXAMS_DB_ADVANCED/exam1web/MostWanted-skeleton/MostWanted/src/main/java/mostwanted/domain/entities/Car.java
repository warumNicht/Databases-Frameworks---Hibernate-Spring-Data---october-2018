package mostwanted.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Comparator;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column
    private BigDecimal price;

    @Column(name = "year_of_production",
            nullable = false)
    private Integer yearOfProduction;

    @Column(name = "max_speed")
    private Double maxSpeed;

    @Column(name = "zero_to_sixty")
    private Double zeroToSixty;

    @ManyToOne
    @JoinColumn(name = "racer_id",referencedColumnName = "id")
    private Racer racer;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getZeroToSixty() {
        return zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public final static class  CarComparator implements Comparator<Car>{

        @Override
        public int compare(Car o1, Car o2) {
            return Integer.compare(o1.getId(),o2.getId());
        }
    }
}
