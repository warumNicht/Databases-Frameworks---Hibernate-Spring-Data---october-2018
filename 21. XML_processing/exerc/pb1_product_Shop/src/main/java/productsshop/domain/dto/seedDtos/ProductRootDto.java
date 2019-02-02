package productsshop.domain.dto.seedDtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductRootDto {
    @XmlElement(name = "product")
    private List<ProductsSeedDto> productsSeedDtos;

    public List<ProductsSeedDto> getProductsSeedDtos() {
        return productsSeedDtos;
    }

    public void setProductsSeedDtos(List<ProductsSeedDto> productsSeedDtos) {
        this.productsSeedDtos = productsSeedDtos;
    }
}
