package pb2_carsDealer.domain.dto.seedDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "parts")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PartRootDto {
    @XmlElement(name = "part")
    List<PartSeedDto> partSeedDtos;

    public List<PartSeedDto> getPartSeedDtos() {
        return partSeedDtos;
    }

    public void setPartSeedDtos(List<PartSeedDto> partSeedDtos) {
        this.partSeedDtos = partSeedDtos;
    }
}
