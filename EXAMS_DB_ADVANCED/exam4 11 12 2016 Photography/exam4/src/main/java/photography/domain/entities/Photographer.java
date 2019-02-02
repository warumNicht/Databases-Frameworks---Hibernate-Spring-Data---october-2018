package photography.domain.entities;

import photography.domain.entities.cameras.Camera;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "photographers")
public class Photographer extends BaseEntity{
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column
    private String phone;

    @ManyToOne
    @JoinColumn(name = "primary_camera_id",nullable = false)
    private Camera primaryCamera;

    @ManyToOne
    @JoinColumn(name = "secondary_camera_id",nullable = false)
    private Camera secondaryCamera;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "photographers_lenses",
    joinColumns = @JoinColumn(name = "photographer_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "lens_id",referencedColumnName = "id"))
    private Set<Lens> lenses;

    @OneToMany(mappedBy = "owner",fetch = FetchType.EAGER)
    private Set<Accessory> accessories;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Camera getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(Camera primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public Camera getSecondaryCamera() {
        return secondaryCamera;
    }

    public void setSecondaryCamera(Camera secondaryCamera) {
        this.secondaryCamera = secondaryCamera;
    }

    public Set<Lens> getLenses() {
        return lenses;
    }

    public void setLenses(Set<Lens> lenses) {
        this.lenses = lenses;
    }

    public Set<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(Set<Accessory> accessories) {
        this.accessories = accessories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName,phone);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        Photographer that = (Photographer) obj;
        return that.getId().equals(this.getId());
    }
}
