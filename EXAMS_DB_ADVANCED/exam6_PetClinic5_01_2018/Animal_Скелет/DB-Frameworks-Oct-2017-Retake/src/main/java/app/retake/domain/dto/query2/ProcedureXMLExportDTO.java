package app.retake.domain.dto.query2;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "procedure")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureXMLExportDTO {
    @XmlAttribute(name = "animal-passport")
    private String animal;

    @XmlElement
    private String owner;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLExportDTO> procedureAnimalAidXMLExportDTOS;


    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ProcedureAnimalAidXMLExportDTO> getProcedureAnimalAidXMLExportDTOS() {
        return procedureAnimalAidXMLExportDTOS;
    }

    public void setProcedureAnimalAidXMLExportDTOS(List<ProcedureAnimalAidXMLExportDTO> procedureAnimalAidXMLExportDTOS) {
        this.procedureAnimalAidXMLExportDTOS = procedureAnimalAidXMLExportDTOS;
    }
}
