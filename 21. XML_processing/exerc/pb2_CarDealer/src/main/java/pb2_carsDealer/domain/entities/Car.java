package pb2_carsDealer.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "cars")
public class Car extends BaseEntity {
    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "travelled_distance")
    private long travelledDistance;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parts_cars",
            joinColumns = @JoinColumn(name = "car_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "part_id",referencedColumnName = "id"))
    private Set<Part> parts;

    public Car() {
        this.parts=new HashSet<>();
    }

    public Car(String make, String model, long travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts=new HashSet<>();
    }
    public BigDecimal getTotalPrice(){
        BigDecimal res=new BigDecimal(0);
        for (Part part : parts) {
            res=res.add(part.getPrice());
        }
        return res;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(make,model,travelledDistance);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if(obj==null){
//            return false;
//        }
//        if(this==obj){
//            return true;
//        }
//        Car that = (Car) obj;
//        return this.getId().equals(that.getId());
//    }
}
