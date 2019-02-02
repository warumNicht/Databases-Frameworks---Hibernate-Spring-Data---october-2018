package photography.domain.dto.importDto.workshops;

import photography.util.XmlDateAdapter;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@XmlRootElement(name = "workshop")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WorkShopImportDto {
    @NotNull
    @XmlAttribute
    private String name;
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    @XmlAttribute(name = "start-date")
    private Date startDate;
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    @XmlAttribute(name = "end-date")
    private Date endDate;
    @NotNull
    @XmlAttribute
    private String location;
    @NotNull
    @XmlAttribute
    private BigDecimal price;
    @NotNull
    @XmlElement
    private String trainer;

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    List<ParticipantDto> participantDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<ParticipantDto> getParticipantDtos() {
        return participantDtos;
    }

    public void setParticipantDtos(List<ParticipantDto> participantDtos) {
        this.participantDtos = participantDtos;
    }
}
