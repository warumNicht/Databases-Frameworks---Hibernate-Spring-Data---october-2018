package productsshop.domain.dto.seedDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProductsSeedDto {
    @Expose
    @NotNull(message = "Name cannot be null!")
    @Size(min = 3, message = "Product should have at least 3 symbols!")
    private String name;

    @Expose
    @NotNull(message = "Price cannot be null!")
    @Positive(message = "Price cannot be 0!")
    @DecimalMin(value = "0",message = "Price must be positive!")
    private BigDecimal price;

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
}
