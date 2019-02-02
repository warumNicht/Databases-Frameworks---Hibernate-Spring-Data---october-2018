package hiberspring.domain.dto.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "branch")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class BranchStatElDto {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String town;
    @XmlAttribute(name = "total_clients")
    private BigDecimal totalClients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }


    public BigDecimal getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(BigDecimal totalClients) {
        this.totalClients = totalClients;
    }
}
