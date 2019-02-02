package pb4_Hospital.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String name;
    @OneToMany(mappedBy = "address",cascade = CascadeType.ALL)
    private Set<Patient> patients;

    public Address() {
        this.patients=new HashSet<>();
    }

    public Address(String name) {
        this.name = name;
        this.patients=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
