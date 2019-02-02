package app.retake.domain.dto.importDtos.procedures;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "animal-aid")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureAnimalAidXMLImportDTO {
    @XmlElement(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
