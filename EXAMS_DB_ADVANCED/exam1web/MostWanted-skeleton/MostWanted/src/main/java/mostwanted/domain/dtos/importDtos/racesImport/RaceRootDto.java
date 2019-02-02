package mostwanted.domain.dtos.importDtos.racesImport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceRootDto {
    @XmlElement(name = "race")
    private List<RaceImportDto> raceImportDtos;

    public List<RaceImportDto> getRaceImportDtos() {
        return raceImportDtos;
    }

    public void setRaceImportDtos(List<RaceImportDto> raceImportDtos) {
        this.raceImportDtos = raceImportDtos;
    }
}
