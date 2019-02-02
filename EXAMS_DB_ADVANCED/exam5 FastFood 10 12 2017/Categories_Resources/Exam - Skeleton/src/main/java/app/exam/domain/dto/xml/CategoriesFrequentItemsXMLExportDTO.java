package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoriesFrequentItemsXMLExportDTO {
    @XmlElement(name = "category")
    List<CategoryExportDTO> categoryExportDTOS;

    public List<CategoryExportDTO> getCategoryExportDTOS() {
        return categoryExportDTOS;
    }

    public void setCategoryExportDTOS(List<CategoryExportDTO> categoryExportDTOS) {
        this.categoryExportDTOS = categoryExportDTOS;
    }

}
