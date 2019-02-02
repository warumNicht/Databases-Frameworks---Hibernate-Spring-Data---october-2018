package pb4_Hospital.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "medicaments")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "medicaments",targetEntity = Diagnose.class,cascade = CascadeType.ALL)
    private Set<Diagnose> diagnoses;

    public Medicament() {
        this.diagnoses=new HashSet<>();
    }

    public Medicament( String name) {
        this.name = name;
        this.diagnoses=new HashSet<>();
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

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }
}
