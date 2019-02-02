package wedding.domain.dto.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TownAllRootDto {
    @XmlElement(name = "town")
    private List<TownWithAgenciesDto> townWithAgenciesDtos;

    public List<TownWithAgenciesDto> getTownWithAgenciesDtos() {
        return townWithAgenciesDtos;
    }

    public void setTownWithAgenciesDtos(List<TownWithAgenciesDto> townWithAgenciesDtos) {
        this.townWithAgenciesDtos = townWithAgenciesDtos;
    }
}
