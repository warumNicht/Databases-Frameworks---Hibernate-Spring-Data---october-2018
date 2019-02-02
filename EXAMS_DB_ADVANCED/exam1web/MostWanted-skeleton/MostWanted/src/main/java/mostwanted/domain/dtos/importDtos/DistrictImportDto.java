package mostwanted.domain.dtos.importDtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DistrictImportDto {
    @NotNull
    @NotEmpty
    private String name;

    private String townName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
