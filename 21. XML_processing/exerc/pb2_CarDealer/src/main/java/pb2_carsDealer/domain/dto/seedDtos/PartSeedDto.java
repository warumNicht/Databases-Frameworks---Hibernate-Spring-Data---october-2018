package pb2_carsDealer.domain.dto.seedDtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
@XmlRootElement(name = "part")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PartSeedDto {
    @XmlAttribute(name = "name")
    @NotNull(message = "Name cannot be null!")
    @NotEmpty(message = "Name cannot be empty!" )
    private String name;

    @XmlAttribute(name = "price")
    @NotNull(message = "Price cannot be null!")
    @DecimalMin(value = "0.01",message = "Price must be positive!")
    private BigDecimal price;

    @XmlAttribute(name = "quantity")
    @NotNull(message = "Quantity cannot be null!")
    @Min(value = 0,message = "Quantity cannot be negative!")
    private Integer quantity;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
