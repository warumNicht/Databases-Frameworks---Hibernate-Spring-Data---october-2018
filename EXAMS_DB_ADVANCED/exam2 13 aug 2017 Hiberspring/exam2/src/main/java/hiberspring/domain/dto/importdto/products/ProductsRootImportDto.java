package hiberspring.domain.dto.importdto.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "products")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProductsRootImportDto {
    @XmlElement(name = "product")
    private List<ProductImportDto> productImportDtos;

    public List<ProductImportDto> getProductImportDtos() {
        return productImportDtos;
    }

    public void setProductImportDtos(List<ProductImportDto> productImportDtos) {
        this.productImportDtos = productImportDtos;
    }
}
