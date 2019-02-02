package pb2_carsDealer.domain.dto.seedDtos;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
@XmlRootElement(name = "customer")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerSeedDto implements Serializable {
    @XmlAttribute(name = "name")
    @NotNull(message = "Name cannot be null!")
    @NotEmpty(message = "Name cannot be empty!" )
    private String name;

    @XmlElement(name = "birth-date",type = Date.class)
    @NotNull(message = "Birth date cannot be null!")
    private Date birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
