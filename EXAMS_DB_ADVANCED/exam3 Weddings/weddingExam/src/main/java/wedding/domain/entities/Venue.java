package wedding.domain.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "venues")
public class Venue extends BaseEntity{
    @Column
    private String name;
    @Column
    private Integer capacity;
    @Column
    private String town;

    @ManyToMany(mappedBy = "venues",fetch = FetchType.EAGER)
    private Set<Wedding> weddings;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Set<Wedding> getWeddings() {
        return weddings;
    }

    public void setWeddings(Set<Wedding> weddings) {
        this.weddings = weddings;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,town,capacity);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return  false;
        }
        if(this==obj){
            return true;
        }
        Venue that = (Venue) obj;
        return that.getId().equals(this.getId());
    }
}
