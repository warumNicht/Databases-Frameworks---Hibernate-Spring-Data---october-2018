package app.exam.domain.dto.xml.export1;

import java.util.List;

public class OrderItemsWrapperDTO {
    private String employeeName;
    private List<OrderExportDto>  orders;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<OrderExportDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderExportDto> orders) {
        this.orders = orders;
    }
}
