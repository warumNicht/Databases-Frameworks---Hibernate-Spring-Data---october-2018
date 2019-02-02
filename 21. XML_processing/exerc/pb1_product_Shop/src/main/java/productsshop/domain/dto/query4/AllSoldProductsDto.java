package productsshop.domain.dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "sold-products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AllSoldProductsDto {
    @XmlAttribute
    private int count;

    @XmlElement(name = "product")
    private List<SimpleSoldProductDto> products;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SimpleSoldProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<SimpleSoldProductDto> products) {
        this.products = products;
    }
}
