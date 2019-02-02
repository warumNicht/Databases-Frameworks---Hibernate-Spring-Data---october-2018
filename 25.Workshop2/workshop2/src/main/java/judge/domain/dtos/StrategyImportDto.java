package judge.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class StrategyImportDto {
    private Integer id;
    @NotNull
    @Pattern(regexp = "^[A-Z].*$")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
