package productsshop.domain.dto.seedDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryRootDto {
    @XmlElement(name = "category")
    private List<CategorySeedDto> categorySeedDtos;

    public List<CategorySeedDto> getCategorySeedDtos() {
        return categorySeedDtos;
    }

    public void setCategorySeedDtos(List<CategorySeedDto> categorySeedDtos) {
        this.categorySeedDtos = categorySeedDtos;
    }
}
