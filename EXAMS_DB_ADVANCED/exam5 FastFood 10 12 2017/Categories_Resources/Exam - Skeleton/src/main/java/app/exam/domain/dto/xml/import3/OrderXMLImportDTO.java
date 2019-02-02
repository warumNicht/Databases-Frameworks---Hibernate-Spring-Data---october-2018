package app.exam.domain.dto.xml.import3;

import app.exam.util.XmlDateAdapter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "order")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class OrderXMLImportDTO {
    @NotEmpty
    @NotNull
    @XmlElement
    private String customer;

    @NotEmpty
    @NotNull
    @XmlElement
    private String employee;

    @NotNull
    @XmlElement
    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    private Date date;

    @NotNull
    @XmlElement
    @Pattern(regexp = "^ForHere$|^ToGo$")
    private String type;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<OrderItemXMLImportDTO> items;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItemXMLImportDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemXMLImportDTO> items) {
        this.items = items;
    }
}
