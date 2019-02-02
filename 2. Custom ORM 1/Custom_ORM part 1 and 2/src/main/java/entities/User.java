package entities;

import ORM.annotations.Column;
import ORM.annotations.Entity;
import ORM.annotations.Id;

import java.text.SimpleDateFormat;
import java.util.Date;
@Entity(name="users")
public final class User {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "notes")
    private Integer notes;

    @Column(name = "city")
    private String city;

    @Column(name = "registration_date")
    private Date registrationDate;

    public User() {
    }


    public User(String name, Integer age, Integer notes, String city, Date registrationDate) {
        this.name = name;
        this.age = age;
        this.notes = notes;
        this.city = city;
        this.registrationDate = registrationDate;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  h:mm:ss");

        String dateString = format.format( this.registrationDate);
        return String.format("User: %s with id=%d, age: %d, Registered on %s",this.name,this.id,this.age, dateString);
    }
}
