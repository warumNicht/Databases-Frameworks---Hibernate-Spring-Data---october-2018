package productsshop.domain.dto.xml;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductUserListDto {
    @XmlElement(name = "product")
    @XmlElementWrapper(name = "products")
    private List<ProductUserDto> products;

    public List<ProductUserDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductUserDto> products) {
        this.products = products;
    }
}
