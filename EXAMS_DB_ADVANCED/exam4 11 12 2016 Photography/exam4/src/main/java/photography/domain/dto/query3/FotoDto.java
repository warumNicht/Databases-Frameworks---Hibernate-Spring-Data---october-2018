package photography.domain.dto.query3;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "photographer")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class FotoDto {
    @XmlAttribute
    private String name;
    @XmlAttribute(name = "primary-camera")
    private String camera;
    @XmlElementWrapper(name = "lenses")
    @XmlElement(name = "lens")
    private List<LenseExportDto> lenseExportDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public List<LenseExportDto> getLenseExportDtos() {
        return lenseExportDtos;
    }

    public void setLenseExportDtos(List<LenseExportDto> lenseExportDtos) {
        this.lenseExportDtos = lenseExportDtos;
    }
}
