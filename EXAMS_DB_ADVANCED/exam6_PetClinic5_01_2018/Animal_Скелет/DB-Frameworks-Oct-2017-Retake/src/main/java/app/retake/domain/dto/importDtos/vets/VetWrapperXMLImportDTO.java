package app.retake.domain.dto.importDtos.vets;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "vets")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class VetWrapperXMLImportDTO {

    @XmlElement(name = "vet")
    private List<VetXMLImportDTO> vetXMLImportDTOS;

    public List<VetXMLImportDTO> getVetXMLImportDTOS() {
        return vetXMLImportDTOS;
    }

    public void setVetXMLImportDTOS(List<VetXMLImportDTO> vetXMLImportDTOS) {
        this.vetXMLImportDTOS = vetXMLImportDTOS;
    }
}
