package pb2_carsDealer.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "parts")
public class Part extends BaseWithName{
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @ManyToMany(mappedBy = "parts")
//    @JoinTable(name = "parts_cars",
//    joinColumns = @JoinColumn(name = "part_id",referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "car_id",referencedColumnName = "id"))
    private Set<Car> cars;

    public Part() {
        super();
        this.cars=new HashSet<>();
    }

    public Part(String name, BigDecimal price, int quantity) {
        super(name);
        this.price = price;
        this.quantity = quantity;
        this.cars=new HashSet<>();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.getId(),super.getName(),price,quantity);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(this==obj){
            return true;
        }
        if(this.getClass()!=obj.getClass()){
            return false;
        }
        Part that = (Part) obj;
        return this.getId().equals(that.getId());
    }
}
