package cars.domain.dto.seedDtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SupplierSeedDto {
    @NotNull(message = "Name cannot be null!")
    @NotEmpty(message = "Name cannot be empty!" )
    private String name;

    @NotNull(message = "Importer cannot be null!")
    private Boolean isImporter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
