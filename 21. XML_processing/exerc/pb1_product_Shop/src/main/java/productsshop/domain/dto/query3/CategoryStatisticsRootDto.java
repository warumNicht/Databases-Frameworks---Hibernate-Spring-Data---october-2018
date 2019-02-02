package productsshop.domain.dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryStatisticsRootDto {
    @XmlElement(name = "category")
    private CategoryStatisticsDto[] categoryStatisticsDtos;

    public CategoryStatisticsDto[] getCategoryStatisticsDtos() {
        return categoryStatisticsDtos;
    }

    public void setCategoryStatisticsDtos(CategoryStatisticsDto[] categoryStatisticsDtos) {
        this.categoryStatisticsDtos = categoryStatisticsDtos;
    }
}
