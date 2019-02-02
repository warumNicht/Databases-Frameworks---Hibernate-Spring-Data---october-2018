package productsshop.domain.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age")
    private int age;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id",referencedColumnName = "id"))
    private Set<User> friends;

    @OneToMany(mappedBy = "seller",fetch = FetchType.EAGER)
    private Set<Product> soldProducts;

    @OneToMany(mappedBy = "buyer")
    private Set<Product> boughtProducts;

    public User() {
        this.friends = new HashSet<>();
        this.soldProducts = new HashSet<>();
        this.boughtProducts = new HashSet<>();
    }

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.friends = new HashSet<>();
        this.soldProducts = new HashSet<>();
        this.boughtProducts = new HashSet<>();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Set<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(Set<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((User) obj).getId());
    }
}
