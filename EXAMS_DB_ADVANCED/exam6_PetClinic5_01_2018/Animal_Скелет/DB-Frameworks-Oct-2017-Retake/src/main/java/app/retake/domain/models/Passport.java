package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport implements Serializable {
    @Id
    @Column(name = "serial_number",length = 10)
    private String serialNumber;
    @OneToOne(mappedBy = "passport")
    private Animal animal;

    @Column(name = "owner_phone_number"
            ,nullable = false,length = 13)
    private String ownerPhoneNumber;

    @Column(name = "owner_name",length = 30)
    private String ownerName;

    @Column(name = "registration_date")
    private Date registrationDate;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
