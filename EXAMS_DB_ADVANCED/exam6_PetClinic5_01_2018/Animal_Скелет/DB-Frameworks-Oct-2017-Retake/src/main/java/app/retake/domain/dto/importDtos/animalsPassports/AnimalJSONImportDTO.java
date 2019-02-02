package app.retake.domain.dto.importDtos.animalsPassports;

import com.sun.istack.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AnimalJSONImportDTO implements Serializable {
   @NotNull
   @Size(min = 3,max = 20)
    private String name;

    @NotNull
    @Size(min = 3,max = 20)
    private String type;

    @Nullable
    @Min(value = 1)
    private Integer age;

    private PassportJSONImportDTO passport;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
