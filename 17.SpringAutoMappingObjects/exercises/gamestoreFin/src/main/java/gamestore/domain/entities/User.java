package gamestore.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "email",nullable = false,unique = true)
    @Pattern(regexp = "^([a-zA-Z0-9]+)([.\\-_][a-zA-Z0-9]+)*@([a-zA-Z0-9]+)([.\\-_][a-zA-Z0-9]+)+$",
    message = "Invalid email")
    private String email;

    @Column(name = "password",nullable = false)
    @Pattern(regexp = "^(?=.*[a-z]).+$",message = "Password should contain at least one lowercase letter!")
    @Pattern(regexp = "^(?=.*[A-Z]).+$",message = "Password should contain at least one uppercase letter!")
    @Pattern(regexp = "^(?=.*[0-9]).+$",message = "Password should contain at least one digit!")
    @Pattern(regexp = "^.{6,}$",message = "Password should have at least 6 symbols!")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$", message = "Invalid password!")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_games",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "game_id",referencedColumnName = "id"))
    private Set<Game> games;

    @OneToMany(mappedBy = "buyer",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Order> orders;

    public User() {
        this.games=new HashSet<>();
        this.orders=new HashSet<>();
    }

    public User(String email, String password, String fullName, Role role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.games=new HashSet<>();
        this.orders=new HashSet<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
