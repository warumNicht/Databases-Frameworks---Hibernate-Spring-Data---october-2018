package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
@XmlRootElement(name = "most-popular-item")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class MostPopularItemDTO {
    @XmlElement
    private String name;
    @XmlElement(name = "total-made")
    private BigDecimal totalMade;
    @XmlElement(name = "times-sold")
    private Long sold;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalMade() {
        return totalMade;
    }

    public void setTotalMade(BigDecimal totalMade) {
        this.totalMade = totalMade;
    }

    public Long getSold() {
        return sold;
    }

    public void setSold(Long sold) {
        this.sold = sold;
    }
}
