package pb3_University.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "students")
public class Student extends Person {
    @Column(name = "average_grade")
    private double averageGrade;

    @Column(name = "attendance")
    private int attendance;

    @ManyToMany(mappedBy = "students",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> studentCourses;

    public Student() {
        super();
        this.studentCourses =new HashSet<>();
    }

    public Student(String firstName, String lastName, String phoneNumber, double averageGrade, int attendance) {
        super(firstName, lastName, phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
        this.studentCourses =new HashSet<>();
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Set<Course> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<Course> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
