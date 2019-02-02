package pb2_SalesDatabase.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "customers" ,schema = "sales")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales;

    public Customer() {
        this.sales=new HashSet<>();
    }

    public Customer(String name, String email, String creditCardNumber) {
        this.name = name;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
        this.sales=new HashSet<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
