package alararestaurant.domain.dtos.importDtos.importOrders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "orders")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class OrdersRootDto {
    @XmlElement(name = "order")
    private List<OrderImportDto> orderImportDtos;


    public List<OrderImportDto> getOrderImportDtos() {
        return orderImportDtos;
    }

    public void setOrderImportDtos(List<OrderImportDto> orderImportDtos) {
        this.orderImportDtos = orderImportDtos;
    }
}
