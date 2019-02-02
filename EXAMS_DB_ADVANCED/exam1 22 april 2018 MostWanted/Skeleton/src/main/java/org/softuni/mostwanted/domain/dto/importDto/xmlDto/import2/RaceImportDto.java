package org.softuni.mostwanted.domain.dto.importDto.xmlDto.import2;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "race")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceImportDto {
    @XmlElement
    private int laps;
    @NotNull
    @XmlElement(name = "district-name")
    private String district;
    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryRaceDto> entries;

    public RaceImportDto() {
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<EntryRaceDto> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryRaceDto> entries) {
        this.entries = entries;
    }
}
