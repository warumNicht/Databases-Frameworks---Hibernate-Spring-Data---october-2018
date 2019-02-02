package mostwanted.domain.dtos.importDtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TownImportDto {
    @NotNull
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
