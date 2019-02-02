package photography.domain.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lenses")
public class Lens extends BaseEntity{
    @Column
    private String make;
    @Column(name = "focal_length")
    private Integer focalLength;
    @Column
    private Double maxAperture;
    @Column(name = "compatible_with")
    private String compatibleWith;

    @ManyToMany(mappedBy = "lenses")
    private Set<Photographer> owners;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public Double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(Double maxAperture) {
        this.maxAperture = maxAperture;
    }


    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public Set<Photographer> getOwners() {
        return owners;
    }

    public void setOwners(Set<Photographer> owners) {
        this.owners = owners;
    }

    @Override
    public int hashCode() {
        return Objects.hash(make,focalLength,compatibleWith,maxAperture);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        Lens that = (Lens) obj;
        return that.getId().equals(this.getId());
    }
}
