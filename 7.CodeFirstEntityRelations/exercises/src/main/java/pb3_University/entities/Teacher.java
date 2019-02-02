package pb3_University.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private double salaryPerHour;

    @OneToMany(mappedBy = "teacher",targetEntity = Course.class,cascade = CascadeType.ALL)
    private Set<Course> courses;


    public Teacher() {
        super();
        this.courses=new HashSet<>();
    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, double salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
        this.courses=new HashSet<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
