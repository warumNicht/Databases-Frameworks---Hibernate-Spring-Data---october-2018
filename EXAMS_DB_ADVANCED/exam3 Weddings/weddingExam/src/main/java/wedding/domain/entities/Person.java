package wedding.domain.entities;

import wedding.domain.entities.enums.Gender;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person extends BaseEntity{
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "middle_name_init",nullable = false)
    private String middleInitial;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "gender",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Column
    private Date birthDate;
    @Column()
    private String phone;
    @Column()
    private String email;

//    @ManyToMany(mappedBy = "guests")
//    private Set<Wedding> weddings;

    public String getFullName(){
        return String.format("%s %s %s",
                this.firstName,this.middleInitial,this.lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Set<Wedding> getWeddings() {
//        return weddings;
//    }
//
//    public void setWeddings(Set<Wedding> weddings) {
//        this.weddings = weddings;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,middleInitial,lastName,email,phone,birthDate);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return  false;
        }
        if(this==obj){
            return true;
        }
        Person that = (Person) obj;
        boolean a = Integer.compare(this.getId(), that.getId()) == 0;
        boolean equals = that.getId().equals(this.getId());
        return equals;
    }
}
