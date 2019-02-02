package productsshop.domain.dto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
@XmlRootElement(name = "employee")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductPriceInRangeDto {
    @XmlAttribute(name = "employee_name")
    private String name;
    @XmlElement(name = "discount-price")
    private BigDecimal price;
    private String seller;

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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
