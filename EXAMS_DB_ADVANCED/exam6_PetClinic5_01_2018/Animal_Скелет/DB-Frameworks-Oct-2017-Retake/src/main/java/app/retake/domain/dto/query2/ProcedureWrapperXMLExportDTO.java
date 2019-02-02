package app.retake.domain.dto.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "procedures")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProcedureWrapperXMLExportDTO {
    @XmlElement(name = "procedure")
    private List<ProcedureXMLExportDTO> procedureXMLExportDTOS;

    public List<ProcedureXMLExportDTO> getProcedureXMLExportDTOS() {
        return procedureXMLExportDTOS;
    }

    public void setProcedureXMLExportDTOS(List<ProcedureXMLExportDTO> procedureXMLExportDTOS) {
        this.procedureXMLExportDTOS = procedureXMLExportDTOS;
    }
}
