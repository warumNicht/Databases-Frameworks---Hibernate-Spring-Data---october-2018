package cars.domain.dto.query4;

import java.math.BigDecimal;

public class PartDto {
    private String Name;
    private BigDecimal Price;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }
}
