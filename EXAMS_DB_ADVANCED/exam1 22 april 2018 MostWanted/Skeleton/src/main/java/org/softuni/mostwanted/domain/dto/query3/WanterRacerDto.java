package org.softuni.mostwanted.domain.dto.query3;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "racer")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WanterRacerDto {
    @XmlAttribute(name = "name")
    private String name;
    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryDto> entries;

    public WanterRacerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntryDto> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryDto> entries) {
        this.entries = entries;
    }
}
