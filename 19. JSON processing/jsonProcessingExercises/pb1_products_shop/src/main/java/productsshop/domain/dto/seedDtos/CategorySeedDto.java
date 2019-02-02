package productsshop.domain.dto.seedDtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategorySeedDto {
    @NotNull(message = "Name cannot be null!")
    @Size(min = 3,max = 15,message = "Name should be between 3 and 15 symbols!")
    private String name;

    public CategorySeedDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
