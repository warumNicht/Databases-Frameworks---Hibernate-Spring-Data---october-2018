package app.exam.domain.dto.xml.export1;

import java.util.List;

public class OrderExportDto {
    private String customer;
    private List<ItemExportDto> items;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<ItemExportDto> getItems() {
        return items;
    }

    public void setItems(List<ItemExportDto> items) {
        this.items = items;
    }
}
