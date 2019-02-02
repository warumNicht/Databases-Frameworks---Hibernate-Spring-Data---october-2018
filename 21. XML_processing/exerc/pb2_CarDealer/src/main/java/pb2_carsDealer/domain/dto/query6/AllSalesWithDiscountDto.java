package pb2_carsDealer.domain.dto.query6;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "sales")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AllSalesWithDiscountDto {
    @XmlElement(name = "sale")
    private List<SaleDiscountDto> saleDiscountDtos;

    public List<SaleDiscountDto> getSaleDiscountDtos() {
        return saleDiscountDtos;
    }

    public void setSaleDiscountDtos(List<SaleDiscountDto> saleDiscountDtos) {
        this.saleDiscountDtos = saleDiscountDtos;
    }
}
