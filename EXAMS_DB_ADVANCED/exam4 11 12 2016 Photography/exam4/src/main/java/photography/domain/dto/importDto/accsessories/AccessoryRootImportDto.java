package photography.domain.dto.importDto.accsessories;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "accessories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AccessoryRootImportDto {
    @XmlElement(name = "accessory")
    private List<AccessoryImportDto> accessoryImportDtos;

    public List<AccessoryImportDto> getAccessoryImportDtos() {
        return accessoryImportDtos;
    }

    public void setAccessoryImportDtos(List<AccessoryImportDto> accessoryImportDtos) {
        this.accessoryImportDtos = accessoryImportDtos;
    }
}
