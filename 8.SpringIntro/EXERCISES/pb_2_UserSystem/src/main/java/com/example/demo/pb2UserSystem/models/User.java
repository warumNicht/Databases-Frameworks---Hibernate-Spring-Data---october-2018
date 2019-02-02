package com.example.demo.pb2UserSystem.models;

import com.example.demo.pb2UserSystem.annotations.Email;
import com.example.demo.pb2UserSystem.annotations.Password;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Length(min = 4,max = 30,message = "Username must have between 4 and 30 symbols!")
    @Column(name = "user_name")
    private String userName;

    @Password(minLength = 5,
            maxLength = 25,
            containsDigit = true,
            containsLowerCase = true,
            containsSpecialSymbols = true,
            containsUpperCase = true
    )
    @Column(name = "password")
    private String password;

    @Email
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "profile_picture_id",referencedColumnName = "id")
    private Picture profilePicture;

    @Column(name = "registered_on")
    private Date registeredOn;

    @Column(name = "last_time_logged_in")
    private Date lastTimeLoggedIn;

    @Range(min = 1,max = 120,message = "Age must be between 1 and 120!")
    @Column(name = "age")
    private int age;

    @Column(name = "is_deleted")
    private boolean deleted;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "born_town_id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "living_town_id")
    private Town livingTown;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_friends",
    joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id",referencedColumnName = "id"))
    private Set<User> friends;

    @OneToMany(mappedBy = "user",targetEntity = Album.class)
    private Set<Album> albums;

    public User() {
        this.albums=new HashSet<>();
        this.friends=new HashSet<>();
    }

    public User(@Length(min = 4, max = 30, message = "Username must have between 4 and 30 symbols") String userName, String password, String email, Picture profilePicture, Date registeredOn, Date lastTimeLoggedIn, @Range(min = 1, max = 120, message = "Age must be between 1 and 120") int age, String firstName, String lastName, Town bornTown, Town livingTown) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.profilePicture = profilePicture;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bornTown = bornTown;
        this.livingTown = livingTown;
        this.albums=new HashSet<>();
        this.friends=new HashSet<>();
    }

    public String getFullName(){
        return this.firstName+" "+this.lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getLivingTown() {
        return livingTown;
    }

    public void setLivingTown(Town livingTown) {
        this.livingTown = livingTown;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,userName,password,email,firstName,lastName,bornTown,livingTown,
                lastTimeLoggedIn,registeredOn,age);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if(!(obj instanceof User)){
            return false;
        }
        User that= (User) obj;
        return this.id==that.id;
    }
}
