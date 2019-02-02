package productsshop.domain.dto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductPriceInRangeListDto {
    @XmlElement(name = "product")
    private List<ProductPriceInRangeDto> products;

    public List<ProductPriceInRangeDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductPriceInRangeDto> products) {
        this.products = products;
    }
}
