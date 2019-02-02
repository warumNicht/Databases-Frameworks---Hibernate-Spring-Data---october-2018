package photography.domain.dto;

import com.google.gson.annotations.SerializedName;

public class PhotographerOrderedDto {
    @SerializedName(value = "FirstName")
    private String firstName;
    @SerializedName(value = "LastName")
    private String lastName;
    @SerializedName(value = "Phone")
    private String phone;

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
}
