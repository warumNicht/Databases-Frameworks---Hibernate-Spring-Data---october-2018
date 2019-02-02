package photography.domain.dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "location")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class LocationWorkExportDto {
    @XmlAttribute
    private String name;
    @XmlElement(name = "workshop")
    private List<WorkShopLocExportDto> workShopLocExportDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkShopLocExportDto> getWorkShopLocExportDtos() {
        return workShopLocExportDtos;
    }

    public void setWorkShopLocExportDtos(List<WorkShopLocExportDto> workShopLocExportDtos) {
        this.workShopLocExportDtos = workShopLocExportDtos;
    }
}
