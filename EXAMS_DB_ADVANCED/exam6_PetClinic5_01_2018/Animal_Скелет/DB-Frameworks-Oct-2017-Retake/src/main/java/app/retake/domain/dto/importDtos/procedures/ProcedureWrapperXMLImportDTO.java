package app.retake.domain.dto.importDtos.procedures;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "procedures")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureWrapperXMLImportDTO {
    @XmlElement(name = "procedure")
    private List<ProcedureXMLImportDTO> procedureXMLImportDTOS;


    public List<ProcedureXMLImportDTO> getProcedureXMLImportDTOS() {
        return procedureXMLImportDTOS;
    }

    public void setProcedureXMLImportDTOS(List<ProcedureXMLImportDTO> procedureXMLImportDTOS) {
        this.procedureXMLImportDTOS = procedureXMLImportDTOS;
    }
}
