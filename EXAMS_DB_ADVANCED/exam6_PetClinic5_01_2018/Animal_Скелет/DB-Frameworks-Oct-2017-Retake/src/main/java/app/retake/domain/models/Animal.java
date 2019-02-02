package app.retake.domain.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal extends BaseEntity{
    @Column(nullable = false,length = 20)
    private String name;
    @Column(nullable = false,length = 20)
    private String type;
    @Column
    private Integer age;
    @OneToOne
    @JoinColumn(name = "passport_serial_number",
    referencedColumnName = "serial_number")
    private Passport passport;

    @OneToMany(mappedBy = "animal")
    private Set<Procedure> procedures;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }
}
