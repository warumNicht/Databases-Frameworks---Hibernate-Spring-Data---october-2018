package org.softuni.mostwanted.domain.dto.importDto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class DistrictImportDto {
    @NotNull
    @NotEmpty
    private String name;
    private String townName;

    public DistrictImportDto() {
    }

    public DistrictImportDto(String name, String townName) {
        this.name = name;
        this.townName = townName;
    }

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
