package wedding.domain.dto.importDto;

import com.google.gson.annotations.SerializedName;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class PeopleImportDto {
    @NotNull
    @Length(min = 1,max = 60)
    private String firstName;
    @NotNull
    @Length(min = 1,max = 1)
    private String middleInitial;
    @NotNull
    @Length(min = 2)
    private String lastName;

    @NotNull
    @Pattern(regexp = "^Male$|^Female$|^NotSpecified$")
    private String gender;

    @SerializedName(value = "birthday")
    private Date  birthDate;

    private String phone;
    @Nullable
    @Pattern(regexp = "^[A-Za-z0-9]+@[a-z]+\\.[a-z]+$")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
