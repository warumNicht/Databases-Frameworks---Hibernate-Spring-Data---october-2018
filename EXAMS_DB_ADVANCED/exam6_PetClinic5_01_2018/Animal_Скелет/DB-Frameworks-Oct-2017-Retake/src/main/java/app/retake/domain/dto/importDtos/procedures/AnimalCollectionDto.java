package app.retake.domain.dto.importDtos.procedures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "animal-aids")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AnimalCollectionDto {
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLImportDTO>procedureAnimalAidXMLImportDTOS;

    public List<ProcedureAnimalAidXMLImportDTO> getProcedureAnimalAidXMLImportDTOS() {
        return procedureAnimalAidXMLImportDTOS;
    }

    public void setProcedureAnimalAidXMLImportDTOS(List<ProcedureAnimalAidXMLImportDTO> procedureAnimalAidXMLImportDTOS) {
        this.procedureAnimalAidXMLImportDTOS = procedureAnimalAidXMLImportDTOS;
    }
}
