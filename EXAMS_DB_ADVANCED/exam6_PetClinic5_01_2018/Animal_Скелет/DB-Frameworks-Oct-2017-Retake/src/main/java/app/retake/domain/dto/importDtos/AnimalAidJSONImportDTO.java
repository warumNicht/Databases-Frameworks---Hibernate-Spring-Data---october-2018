package app.retake.domain.dto.importDtos;



import com.sun.istack.Nullable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

public class AnimalAidJSONImportDTO implements Serializable {
    @NotNull
    @Size(min = 3)
    private String name;

    @Nullable
    @DecimalMin(value = "0.01")
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
