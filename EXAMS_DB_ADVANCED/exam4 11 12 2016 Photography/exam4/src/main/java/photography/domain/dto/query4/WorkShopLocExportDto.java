package photography.domain.dto.query4;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "workshop")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WorkShopLocExportDto {
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "total-profit")
    private String totalProfit;

    @XmlElement(name = "participants")
    private ParticipantCountExportDto participantCountExportDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }

    public ParticipantCountExportDto getParticipantCountExportDto() {
        return participantCountExportDto;
    }

    public void setParticipantCountExportDto(ParticipantCountExportDto participantCountExportDto) {
        this.participantCountExportDto = participantCountExportDto;
    }
}
