package photography.domain.dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "participants")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ParticipantCountExportDto {
    @XmlAttribute
    private Integer count;
    @XmlElement(name = "participant")
    private List<ParticipantExportDto> participantExportDtos;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ParticipantExportDto> getParticipantExportDtos() {
        return participantExportDtos;
    }

    public void setParticipantExportDtos(List<ParticipantExportDto> participantExportDtos) {
        this.participantExportDtos = participantExportDtos;
    }
}
