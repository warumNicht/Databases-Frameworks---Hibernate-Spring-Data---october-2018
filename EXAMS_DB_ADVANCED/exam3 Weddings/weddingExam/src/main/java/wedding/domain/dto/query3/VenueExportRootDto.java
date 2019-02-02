package wedding.domain.dto.query3;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "venues")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class VenueExportRootDto {
    @XmlAttribute
    private String town;

    @XmlElement(name = "venue")
    private List<VenuSofiaDto> venuSofiaDtos;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public List<VenuSofiaDto> getVenuSofiaDtos() {
        return venuSofiaDtos;
    }

    public void setVenuSofiaDtos(List<VenuSofiaDto> venuSofiaDtos) {
        this.venuSofiaDtos = venuSofiaDtos;
    }
}
