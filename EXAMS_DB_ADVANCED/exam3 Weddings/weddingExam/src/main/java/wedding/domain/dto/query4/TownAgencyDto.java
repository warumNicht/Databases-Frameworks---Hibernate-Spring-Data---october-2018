package wedding.domain.dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "agency")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownAgencyDto {
    @XmlAttribute
    private String  name;
    @XmlAttribute
    private String profit;
    @XmlElement(name = "wedding")
    private List<TownWeddingDto> weddingDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public List<TownWeddingDto> getWeddingDtos() {
        return weddingDtos;
    }

    public void setWeddingDtos(List<TownWeddingDto> weddingDtos) {
        this.weddingDtos = weddingDtos;
    }
}
