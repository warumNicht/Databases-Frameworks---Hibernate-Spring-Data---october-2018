package pb4_Hospital.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
@Entity
@Table(name = "visitations")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    private Patient patient;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnose_id",referencedColumnName = "id")
    private Diagnose diagnose;

    public Visitation() {
    }

    public Visitation(Date date, String comments, Patient patient, Diagnose diagnose) {
        this.date = date;
        this.comments = comments;
        this.patient = patient;
        this.diagnose = diagnose;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    @Override
    public String toString() {
        String pattern = "dd-MMM-yyyy  HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(this.date);

        StringBuilder res=new StringBuilder();
        res.append(String.format("Visitation from %s with patient %s %s%n",date,this.patient.getFirstName(),this.patient.getLastName()));
        res.append(this.diagnose);


        return res.toString();
    }
}
