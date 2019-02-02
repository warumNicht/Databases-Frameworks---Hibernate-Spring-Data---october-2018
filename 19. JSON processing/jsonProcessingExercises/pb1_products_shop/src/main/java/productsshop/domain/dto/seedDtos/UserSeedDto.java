package productsshop.domain.dto.seedDtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserSeedDto {
    private String firstName;

    @NotNull(message = "Last name cannot be null!")
    @Size(min = 3, message = "Last name must have at least 3 symbols!")
    private String lastName;

    @Min(value = 0,message = "Age must be positive!" )
    private int age;

    public UserSeedDto(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
