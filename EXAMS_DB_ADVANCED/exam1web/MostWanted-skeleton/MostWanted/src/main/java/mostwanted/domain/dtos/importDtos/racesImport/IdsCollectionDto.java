package mostwanted.domain.dtos.importDtos.racesImport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "entries")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class IdsCollectionDto {
    @XmlElement(name = "entry")
    private List<EntryIdImportDto> entryIdImportDtos;

    public List<EntryIdImportDto> getEntryIdImportDtos() {
        return entryIdImportDtos;
    }

    public void setEntryIdImportDtos(List<EntryIdImportDto> entryIdImportDtos) {
        this.entryIdImportDtos = entryIdImportDtos;
    }
}
