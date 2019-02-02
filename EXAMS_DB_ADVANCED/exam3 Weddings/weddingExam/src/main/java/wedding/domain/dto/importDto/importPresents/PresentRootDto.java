package wedding.domain.dto.importDto.importPresents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "presents")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PresentRootDto {
    @XmlElement(name = "present")
    private List<PresentImportDto> presentImportDtos;

    public List<PresentImportDto> getPresentImportDtos() {
        return presentImportDtos;
    }

    public void setPresentImportDtos(List<PresentImportDto> presentImportDtos) {
        this.presentImportDtos = presentImportDtos;
    }
}
