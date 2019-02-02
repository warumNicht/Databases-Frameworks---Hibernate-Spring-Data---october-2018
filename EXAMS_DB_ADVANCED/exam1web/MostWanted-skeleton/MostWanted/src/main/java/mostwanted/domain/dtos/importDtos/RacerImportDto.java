package mostwanted.domain.dtos.importDtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RacerImportDto {
    @NotNull
    private String name;
    private Integer age;
    private Double bounty;
    private String homeTown;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getBounty() {
        return bounty;
    }

    public void setBounty(Double bounty) {
        this.bounty = bounty;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
