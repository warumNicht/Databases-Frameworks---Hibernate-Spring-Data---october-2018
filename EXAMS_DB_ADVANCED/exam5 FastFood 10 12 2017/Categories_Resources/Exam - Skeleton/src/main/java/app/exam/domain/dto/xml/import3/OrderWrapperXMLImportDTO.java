package app.exam.domain.dto.xml.import3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "orders")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class OrderWrapperXMLImportDTO {
    @XmlElement(name = "order")
    private List<OrderXMLImportDTO> orderXMLImportDTOS;

    public List<OrderXMLImportDTO> getOrderXMLImportDTOS() {
        return orderXMLImportDTOS;
    }

    public void setOrderXMLImportDTOS(List<OrderXMLImportDTO> orderXMLImportDTOS) {
        this.orderXMLImportDTOS = orderXMLImportDTOS;
    }
}
