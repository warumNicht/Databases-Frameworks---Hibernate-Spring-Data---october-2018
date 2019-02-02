package alararestaurant.domain.dtos.importDtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeImportDto {
    @NotNull
    @Size(min = 3,max = 30)
    private String name;

    @NotNull
    @Min(value = 15)
    @Max(value = 80)
    private Integer age;

    @NotNull
    @Size(min = 3,max = 30)
    private String position;


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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
