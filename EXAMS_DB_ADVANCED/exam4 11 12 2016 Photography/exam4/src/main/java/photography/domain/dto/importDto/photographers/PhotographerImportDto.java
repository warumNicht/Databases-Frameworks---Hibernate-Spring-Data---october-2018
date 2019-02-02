package photography.domain.dto.importDto.photographers;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PhotographerImportDto {
    @NotNull
    private String firstName;
    @NotNull
    @Length(min = 2,max = 50)
    private String lastName;

    @Nullable
    @Pattern(regexp = "^\\+[0-9]{1,3}\\/[0-9]{8,10}$")
    private String phone;
    private Integer[] lenses;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer[] getLenses() {
        return lenses;
    }

    public void setLenses(Integer[] lenses) {
        this.lenses = lenses;
    }
}
