package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.JSONParser;
import app.exam.util.TextUtil;
import app.exam.parser.interfaces.ValidationUtil;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class EmployeesController {
    private EmployeeService employeeService;
    private JSONParser gson;
    private ValidationUtil validationUtil;
@Autowired
    public EmployeesController(EmployeeService employeeService, JSONParser gson, ValidationUtil validationUtil) {
        this.employeeService = employeeService;
    this.gson = gson;
    this.validationUtil = validationUtil;
}

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        EmployeeJSONImportDTO[] read = this.gson.read(EmployeeJSONImportDTO[].class, jsonContent);
        StringBuilder res=new StringBuilder();
        for (EmployeeJSONImportDTO employeeJSONImportDTO : read) {
            if (!this.validationUtil.isValid(employeeJSONImportDTO)){
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }

            this.employeeService.create(employeeJSONImportDTO);
            res.append(String.format(TextUtil.SUCCESS_MESSAGE_1PARAM,employeeJSONImportDTO.getName()))
                    .append(System.lineSeparator());
        }
        return res.toString().trim();
    }
}
