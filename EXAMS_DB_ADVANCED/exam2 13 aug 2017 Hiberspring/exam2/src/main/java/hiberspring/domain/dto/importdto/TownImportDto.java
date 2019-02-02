package hiberspring.domain.dto.importdto;

import javax.validation.constraints.NotNull;

public class TownImportDto {
    @NotNull
    private String name;

    @NotNull
    private Integer population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
