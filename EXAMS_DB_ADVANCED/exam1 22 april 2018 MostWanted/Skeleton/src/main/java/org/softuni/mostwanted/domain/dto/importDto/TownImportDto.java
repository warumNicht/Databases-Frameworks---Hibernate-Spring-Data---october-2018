package org.softuni.mostwanted.domain.dto.importDto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class TownImportDto {
    @NotNull
    @NotEmpty
    private String name;

    public TownImportDto() {
    }

    public TownImportDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
