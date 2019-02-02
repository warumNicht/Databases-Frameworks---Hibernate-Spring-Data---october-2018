package photography.domain.dto.query3;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "lens")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class LenseExportDto {
    @XmlElement(name = "lens")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
