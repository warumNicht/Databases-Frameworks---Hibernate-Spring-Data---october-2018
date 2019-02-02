package pb4_Hospital.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "comments")
    private String comments;

    @OneToOne(mappedBy = "diagnose",targetEntity = Visitation.class,cascade = CascadeType.ALL)
    private Visitation visitation;


    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "medicaments_diagnoses",
            joinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id",referencedColumnName = "id"))
    private Set<Medicament> medicaments;

    public Diagnose() {
        this.medicaments=new HashSet<>();
    }


    public Diagnose(String name, String comments) {
        this.name = name;
        this.comments = comments;
        this.medicaments=new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public Visitation getVisitation() {
        return visitation;
    }

    public void setVisitation(Visitation visitation) {
        this.visitation = visitation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append(String.format("Diagnose: %s%nKommentar: %s%n",this.name,this.comments));
        List<String> medicaments=this.medicaments.stream()
                .map(x->x.getName())
                .collect(Collectors.toList());
        res.append(String.format("Medikaments: %s", String.join(", ",medicaments)));


        return res.toString();
    }
}
