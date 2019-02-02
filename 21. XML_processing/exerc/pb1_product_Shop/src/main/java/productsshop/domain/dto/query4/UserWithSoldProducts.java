package productsshop.domain.dto.query4;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserWithSoldProducts {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private int age;

    @XmlElement(name = "sold-products")
    private AllSoldProductsDto soldProducts;

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

    public AllSoldProductsDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(AllSoldProductsDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
