package hiberspring.domain.dto.importdto.employeesImport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class EmployeeRootImportDto {
    @XmlElement(name = "employee")
    private List<EmployeImportDto> employees;

    public List<EmployeImportDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeImportDto> employees) {
        this.employees = employees;
    }
}
