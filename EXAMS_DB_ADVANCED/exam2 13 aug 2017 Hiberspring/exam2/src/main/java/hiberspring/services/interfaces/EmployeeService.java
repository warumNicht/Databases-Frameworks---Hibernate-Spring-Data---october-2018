package hiberspring.services.interfaces;

import hiberspring.domain.dto.ProductivEmployeeDto;
import hiberspring.domain.dto.importdto.employeesImport.EmployeeRootImportDto;

public interface EmployeeService {
    String importEmployees(EmployeeRootImportDto employeeRootImportDto);

    ProductivEmployeeDto[] productiveEmployees();
}
