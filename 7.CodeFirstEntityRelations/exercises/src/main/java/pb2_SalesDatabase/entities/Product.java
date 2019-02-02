package pb2_SalesDatabase.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "products",schema = "sales")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;

    public Product() {
        this.sales=new HashSet<>();
    }

    public Product(String name, Double quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
