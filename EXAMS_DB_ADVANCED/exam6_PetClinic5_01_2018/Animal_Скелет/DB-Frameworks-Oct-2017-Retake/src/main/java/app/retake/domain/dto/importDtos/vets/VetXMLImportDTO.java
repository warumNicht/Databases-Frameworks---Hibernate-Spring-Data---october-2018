package app.retake.domain.dto.importDtos.vets;

import com.sun.istack.Nullable;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vet")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class VetXMLImportDTO {
    @Nullable
    @Size(min = 3,max = 40)
    @XmlElement
    private String name;

    @Nullable
    @Size(min = 3,max = 50)
    @XmlElement
    private String profession;

    @Nullable
    @Min(value = 22)
    @Max(value = 65)
    @XmlElement
    private Integer age;

    @NotNull
    @Pattern(regexp = "^\\+359[0-9]{9}$|^0[0-9]{9}$")
    @XmlElement(name = "phone-number")
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
