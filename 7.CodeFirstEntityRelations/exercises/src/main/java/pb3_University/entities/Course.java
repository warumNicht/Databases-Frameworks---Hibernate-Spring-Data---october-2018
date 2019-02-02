package pb3_University.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "credits")
    private int credits;

    @ManyToOne(targetEntity = Person.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id")
    private Person teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"))

    private Set<Person> students;

    public Course() {
        this.students=new HashSet<>();
    }

    public Course(String name, String description, Date startDate, Date endDate, int credits, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credits = credits;
        this.teacher = teacher;
        this.students=new HashSet<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public Set<Person> getStudents() {
        return students;
    }

    public void setStudents(Set<Person> students) {
        this.students = students;
    }
}
