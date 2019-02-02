package pb2_carsDealer.domain.dto.seedDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerRootDto {
    @XmlElement(name = "customer")
    private List<CustomerSeedDto> customerSeedDtos;

    public List<CustomerSeedDto> getCustomerSeedDtos() {
        return customerSeedDtos;
    }

    public void setCustomerSeedDtos(List<CustomerSeedDto> customerSeedDtos) {
        this.customerSeedDtos = customerSeedDtos;
    }
}
