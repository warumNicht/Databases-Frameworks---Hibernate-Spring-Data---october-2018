package photography.domain.dto.importDto.workshops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "workshops")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WorkshopRootDto {
    @XmlElement(name = "workshop")
    private List<WorkShopImportDto> workShopImportDtos;

    public List<WorkShopImportDto> getWorkShopImportDtos() {
        return workShopImportDtos;
    }

    public void setWorkShopImportDtos(List<WorkShopImportDto> workShopImportDtos) {
        this.workShopImportDtos = workShopImportDtos;
    }
}
