package photography.domain.dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "photographers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RootFotoExportDto {
    @XmlElement(name = "photographer")
    private List<FotoDto> fotoDtos;


    public List<FotoDto> getFotoDtos() {
        return fotoDtos;
    }

    public void setFotoDtos(List<FotoDto> fotoDtos) {
        this.fotoDtos = fotoDtos;
    }
}
