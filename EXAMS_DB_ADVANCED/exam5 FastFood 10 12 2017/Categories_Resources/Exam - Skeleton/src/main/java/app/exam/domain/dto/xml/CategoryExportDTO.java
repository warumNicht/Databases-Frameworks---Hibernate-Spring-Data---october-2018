package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "category")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryExportDTO {
    @XmlElement
    private String name;
    @XmlElement(name = "most-popular-item")
    private MostPopularItemDTO mostPopularItemDTOS;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MostPopularItemDTO getMostPopularItemDTOS() {
        return mostPopularItemDTOS;
    }

    public void setMostPopularItemDTOS(MostPopularItemDTO mostPopularItemDTOS) {
        this.mostPopularItemDTOS = mostPopularItemDTOS;
    }
}
