package app.exam.service.api;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.xml.export1.OrderItemsWrapperDTO;
import app.exam.domain.dto.xml.import3.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.import3.OrderXMLImportDTO;

import java.text.ParseException;

public interface OrderService {
    void create(OrderXMLImportDTO dto) throws ParseException;

    OrderItemsWrapperDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType);
}
