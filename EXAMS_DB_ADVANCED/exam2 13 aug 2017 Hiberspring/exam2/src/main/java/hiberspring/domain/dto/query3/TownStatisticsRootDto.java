package hiberspring.domain.dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "towns")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownStatisticsRootDto {
    @XmlElement(name = "towns")
    private List<TownStatisticsElementDto> elementDtos;

    public List<TownStatisticsElementDto> getElementDtos() {
        return elementDtos;
    }

    public void setElementDtos(List<TownStatisticsElementDto> elementDtos) {
        this.elementDtos = elementDtos;
    }
}
