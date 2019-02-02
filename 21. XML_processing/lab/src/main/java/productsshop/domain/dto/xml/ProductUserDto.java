package productsshop.domain.dto.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

//@XmlRootElement(name = "product")
//@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductUserDto {
//    @XmlElement
    private String name;
 //   @XmlElement
    private BigDecimal price;
 //   @XmlElement
    private SimpleUserDto seller;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SimpleUserDto getSeller() {
        return seller;
    }

    public void setSeller(SimpleUserDto seller) {
        this.seller = seller;
    }
}
