package wedding.domain.dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "town")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownWithAgenciesDto {
    @XmlAttribute
    private String name;
    @XmlElementWrapper(name = "agencies")
    @XmlElement(name = "agency")
    private List<TownAgencyDto> townAgencyDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TownAgencyDto> getTownAgencyDtos() {
        return townAgencyDtos;
    }

    public void setTownAgencyDtos(List<TownAgencyDto> townAgencyDtos) {
        this.townAgencyDtos = townAgencyDtos;
    }
}
