package photography.domain.dto.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "locations")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class LocRootExportDto {
    @XmlElement(name = "location")
    private List<LocationWorkExportDto> locRootExportDtos;

    public List<LocationWorkExportDto> getLocRootExportDtos() {
        return locRootExportDtos;
    }

    public void setLocRootExportDtos(List<LocationWorkExportDto> locRootExportDtos) {
        this.locRootExportDtos = locRootExportDtos;
    }
}
