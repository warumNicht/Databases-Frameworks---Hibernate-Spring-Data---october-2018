package wedding.domain.dto.query3;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "venue")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class VenuSofiaDto {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private Integer capacity;
    @XmlElement(name = "weddings-count")
    private Integer count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
