package productsshop.domain.dto;

import java.math.BigDecimal;

public class CategoryStatisticsDto {
    private String category;
    private long productCount;
    private Double averagePrice;
    private BigDecimal totalRevenue;

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
