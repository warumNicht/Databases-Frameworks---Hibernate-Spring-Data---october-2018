package org.softuni.mostwanted.domain.dto.importDto.xmlDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "race-entries")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceEntryImportRootDto {
    @XmlElement(name = "race-entry")
    private List<RaceEntryImportDto> raceEntryImportDtos;

    public RaceEntryImportRootDto() {
    }

    public List<RaceEntryImportDto> getRaceEntryImportDtos() {
        return raceEntryImportDtos;
    }

    public void setRaceEntryImportDtos(List<RaceEntryImportDto> raceEntryImportDtos) {
        this.raceEntryImportDtos = raceEntryImportDtos;
    }
}
