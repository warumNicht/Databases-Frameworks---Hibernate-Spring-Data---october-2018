package wedding.domain.dto.query4;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "guest")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownGuestDto {
    @XmlAttribute
    private String family;
    @XmlValue
    private String fullName;

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
