package pb2_carsDealer.domain.dto.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AllOrderedCustomersDto {
    @XmlElement(name = "customer")
    private List<OrderedCustomerDto> allOrderedCustomersDtos;

    public List<OrderedCustomerDto> getAllOrderedCustomersDtos() {
        return allOrderedCustomersDtos;
    }

    public void setAllOrderedCustomersDtos(List<OrderedCustomerDto> allOrderedCustomerDtos) {
        this.allOrderedCustomersDtos = allOrderedCustomerDtos;
    }
}
