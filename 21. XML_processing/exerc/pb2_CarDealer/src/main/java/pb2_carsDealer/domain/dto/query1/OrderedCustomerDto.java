package pb2_carsDealer.domain.dto.query1;
import pb2_carsDealer.util.XmlDateAdapter;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "customer")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class OrderedCustomerDto {
    private Integer id;
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private Date birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
