package mostwanted.domain.dtos.importDtos.racesImport;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RaceImportDto {
    @NotNull
    @XmlElement
    private Integer laps;
    @NotNull
    @XmlElement(name = "district-name")
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryIdImportDto> entryIdImportDtos;

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<EntryIdImportDto> getEntryIdImportDtos() {
        return entryIdImportDtos;
    }

    public void setEntryIdImportDtos(List<EntryIdImportDto> entryIdImportDtos) {
        this.entryIdImportDtos = entryIdImportDtos;
    }
}
