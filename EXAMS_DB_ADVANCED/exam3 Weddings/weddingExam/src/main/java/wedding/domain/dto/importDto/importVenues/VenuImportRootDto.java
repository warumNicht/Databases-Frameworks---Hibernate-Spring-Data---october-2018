package wedding.domain.dto.importDto.importVenues;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "venues")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class VenuImportRootDto {
    @XmlElement(name = "venue")
    private List<VenueImportDto> venueImportDtos;

    public List<VenueImportDto> getVenueImportDtos() {
        return venueImportDtos;
    }

    public void setVenueImportDtos(List<VenueImportDto> venueImportDtos) {
        this.venueImportDtos = venueImportDtos;
    }
}
