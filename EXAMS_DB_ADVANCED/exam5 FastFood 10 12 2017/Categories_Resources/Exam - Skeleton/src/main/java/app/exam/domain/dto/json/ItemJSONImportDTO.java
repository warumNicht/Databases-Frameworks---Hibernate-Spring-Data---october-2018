package app.exam.domain.dto.json;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemJSONImportDTO {
    @NotNull
    @NotEmpty
    @Length(min = 3,max = 30)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3,max = 30)
    private String category;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
