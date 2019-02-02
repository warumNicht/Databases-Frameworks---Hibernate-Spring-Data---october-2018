package pb2_carsDealer.domain.dto.query5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AllCustomersStatisticsDto {
    @XmlElement(name = "customer")
    private List<CustomerStatisticsDto> customerStatisticsDtos;

    public List<CustomerStatisticsDto> getCustomerStatisticsDtos() {
        return customerStatisticsDtos;
    }

    public void setCustomerStatisticsDtos(List<CustomerStatisticsDto> customerStatisticsDtos) {
        this.customerStatisticsDtos = customerStatisticsDtos;
    }
}
