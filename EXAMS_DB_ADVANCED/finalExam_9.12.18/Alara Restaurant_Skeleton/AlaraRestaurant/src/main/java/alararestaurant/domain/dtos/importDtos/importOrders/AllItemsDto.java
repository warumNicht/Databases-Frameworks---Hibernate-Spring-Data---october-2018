package alararestaurant.domain.dtos.importDtos.importOrders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "items")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AllItemsDto {
    @XmlElement(name = "item")
    private List<ItemOrderImportDto> itemOrderImportDtos;



    public List<ItemOrderImportDto> getItemOrderImportDtos() {
        return itemOrderImportDtos;
    }

    public void setItemOrderImportDtos(List<ItemOrderImportDto> itemOrderImportDtos) {
        this.itemOrderImportDtos = itemOrderImportDtos;
    }
}
