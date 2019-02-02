package productsshop.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "categories")
//    @JoinTable(name = "category_products",
//    joinColumns = @JoinColumn(name = "category_id",referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> products;

    public Category() {
        this.products=new HashSet<>();
    }

    public Category( String name) {
        this.name = name;
        this.products=new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null){
            return false;
        }
        if(this==obj){
            return true;
        }
        Category that=(Category)obj;
        return this.getId().equals(that.getId());
    }
}
