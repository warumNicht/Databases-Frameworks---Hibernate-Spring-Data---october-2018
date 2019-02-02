package org.softuni.mostwanted.domain.dto.importDto.xmlDto.import2;

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

    public RaceRootDto() {
    }

    public List<RaceImportDto> getRaceImportDtos() {
        return raceImportDtos;
    }

    public void setRaceImportDtos(List<RaceImportDto> raceImportDtos) {
        this.raceImportDtos = raceImportDtos;
    }
}
