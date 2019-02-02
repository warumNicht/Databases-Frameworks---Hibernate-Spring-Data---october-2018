package app.exam.controller;

import app.exam.domain.dto.xml.export1.OrderItemsWrapperDTO;
import app.exam.domain.dto.xml.import3.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.import3.OrderXMLImportDTO;
import app.exam.domain.dto.xml.import3.OrderWrapperXMLImportDTO;
import app.exam.domain.entities.Employee;
import app.exam.parser.JSONParser;
import app.exam.parser.XMLParser;
import app.exam.parser.interfaces.ValidationUtil;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.OrderService;
import app.exam.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class OrdersController {
    private OrderService orderService;
    private EmployeeRepository employeeRepository;
    private ItemsRepository itemsRepository;
    private ValidationUtil validationUtil;
    private XMLParser xmlParser;
    private JSONParser gson;


    @Autowired
    public OrdersController(OrderService orderService, EmployeeRepository employeeRepository, ItemsRepository itemsRepository, ValidationUtil validationUtil,
                            XMLParser xmlParser, JSONParser gson) {
        this.orderService = orderService;
        this.employeeRepository = employeeRepository;
        this.itemsRepository = itemsRepository;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;

        this.gson = gson;
    }

    public String importDataFromXML(String xmlContent) throws JAXBException, FileNotFoundException, ParseException {
        OrderWrapperXMLImportDTO orderWrapperXMLImportDTO = this.xmlParser.
                read(OrderWrapperXMLImportDTO.class, xmlContent);

        StringBuilder res = new StringBuilder();
        for (OrderXMLImportDTO orderXMLImportDTO : orderWrapperXMLImportDTO.getOrderXMLImportDTOS()) {
            if (!this.validationUtil.isValid(orderXMLImportDTO)) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            String employee = orderXMLImportDTO.getEmployee();
            Employee employee1 = this.employeeRepository.getByName(employee).orElse(null);
            if (employee1 == null) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            boolean isValid = true;
            for (OrderItemXMLImportDTO orderItemXMLImportDTO : orderXMLImportDTO.getItems()) {
                if (!this.itemsRepository.existsByName(orderItemXMLImportDTO.getName())) {
                    res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            this.orderService.create(orderXMLImportDTO);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            String dateString = format.format(orderXMLImportDTO.getDate());
            res.append(String.format("Order for %s on %s added.",
                    orderXMLImportDTO.getCustomer(), dateString))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) throws IOException, JAXBException {

        OrderItemsWrapperDTO orderItemsWrapperDTO = this.orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType);
        String res = this.gson.write(orderItemsWrapperDTO);
        return res;
    }
}
