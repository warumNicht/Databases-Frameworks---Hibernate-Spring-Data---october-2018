package app.retake.domain.dto.importDtos.animalsPassports;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PassportJSONImportDTO implements Serializable {
    @NotNull
    @Pattern(regexp = "^.{7}[0-9]{3}$")
    private String serialNumber;

    @NotNull
    @Size(min = 3,max = 30)
    private String ownerName;

    @NotNull
    @Pattern(regexp = "^\\+359[0-9]{9}$|^0[0-9]{9}$")
    private String ownerPhoneNumber;

    private String registrationDate;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
