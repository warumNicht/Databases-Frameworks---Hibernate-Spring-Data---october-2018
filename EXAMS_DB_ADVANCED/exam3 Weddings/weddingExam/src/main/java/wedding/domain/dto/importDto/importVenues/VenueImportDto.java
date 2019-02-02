package wedding.domain.dto.importDto.importVenues;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "venue")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class VenueImportDto {
    @XmlAttribute
    private String name;
    @XmlElement
    private Integer capacity;
    @XmlElement
    private String town;

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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
