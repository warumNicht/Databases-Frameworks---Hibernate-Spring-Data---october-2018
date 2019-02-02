package productsshop.domain.dto.query3;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
@XmlRootElement(name = "category")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryStatisticsDto {
    @XmlAttribute(name = "name")
    private String category;

    @XmlElement(name = "products-count")
    private long productCount;

    @XmlElement(name = "average-price")
    private Double averagePrice;

    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryStatisticsDto() {
    }

    public CategoryStatisticsDto(String name, long productCount, Double averagePrice, BigDecimal totalRevenue) {
        this.category = name;
        this.productCount = productCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getName() {
        return category;
    }

    public void setName(String name) {
        this.category = name;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
