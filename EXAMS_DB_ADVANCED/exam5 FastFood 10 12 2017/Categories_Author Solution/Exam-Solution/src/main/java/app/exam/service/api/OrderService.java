package app.exam.service.api;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import org.springframework.data.jpa.repository.Query;

import java.text.ParseException;

public interface OrderService {
    void create(OrderXMLImportDTO dto) throws ParseException;
    EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType);
}
