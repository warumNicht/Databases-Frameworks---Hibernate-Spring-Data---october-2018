package productsshop.domain.dto.query4;

import java.util.List;

public class AllSoldProductsDto {
    private int count;
    private List<SimpleSoldProductDto> products;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SimpleSoldProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<SimpleSoldProductDto> products) {
        this.products = products;
    }
}
