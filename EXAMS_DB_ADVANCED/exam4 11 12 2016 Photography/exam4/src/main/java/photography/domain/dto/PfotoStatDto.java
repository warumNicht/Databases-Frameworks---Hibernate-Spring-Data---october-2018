package photography.domain.dto;

import com.google.gson.annotations.SerializedName;

public class PfotoStatDto {
    @SerializedName(value = "FirstName")
    private String firstName;
    @SerializedName(value = "LastName")
    private String lastName;
    @SerializedName(value = "CameraMake")
    private String camera;
    @SerializedName(value = "LensesCount")
    private Integer count;

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

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
