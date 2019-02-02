package alararestaurant.domain.dtos.importDtos.importOrders;

import alararestaurant.util.XmlDateAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "order")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class OrderImportDto {
    @XmlElement
    @NotNull
    private String customer;

    @NotNull
    @XmlElement
    private String employee;

    @NotNull
    @XmlElement(name = "dateTime-time")
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private Date dateTime;

    @NotNull
    @Pattern(regexp = "^ForHere$|^ToGo$")
    @XmlElement
    private String type;

    @XmlElement(name = "items")
    private AllItemsDto allItemsDto;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AllItemsDto getAllItemsDto() {
        return allItemsDto;
    }

    public void setAllItemsDto(AllItemsDto allItemsDto) {
        this.allItemsDto = allItemsDto;
    }
}
