package productsshop.domain.dto.query2;

import productsshop.domain.dto.query2.ProductSoldDto;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UserSoldDto {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElementWrapper(name = "sold-products")
    @XmlElement(name = "product")
    private List<ProductSoldDto> soldProducts;

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

    public List<ProductSoldDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSoldDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
