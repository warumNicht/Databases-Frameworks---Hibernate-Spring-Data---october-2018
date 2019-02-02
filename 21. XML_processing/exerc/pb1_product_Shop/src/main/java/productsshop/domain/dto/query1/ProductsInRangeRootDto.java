package productsshop.domain.dto.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductsInRangeRootDto {
    @XmlElement(name = "product")
    private ProductPriceInRangeDto[] productSoldDtos;

    public ProductPriceInRangeDto[] getProductSoldDtos() {
        return productSoldDtos;
    }

    public void setProductSoldDtos(ProductPriceInRangeDto[] productSoldDtos) {
        this.productSoldDtos = productSoldDtos;
    }
}
