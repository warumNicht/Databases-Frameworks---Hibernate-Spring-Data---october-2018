package hiberspring.services;

import hiberspring.domain.dto.ProductivEmployeeDto;
import hiberspring.domain.dto.importdto.employeesImport.EmployeImportDto;
import hiberspring.domain.dto.importdto.employeesImport.EmployeeRootImportDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.CardRepository;
import hiberspring.repositories.EmployeeRepository;
import hiberspring.services.interfaces.EmployeeService;
import hiberspring.util.TextUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private CardRepository cardRepository;
    private BranchRepository branchRepository;
    private ModelMapper mapper;
    private ValidationUtil validationUtil;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CardRepository cardRepository,
                               BranchRepository branchRepository, ModelMapper mapper,
                               ValidationUtil validationUtil) {
        this.employeeRepository = employeeRepository;
        this.cardRepository = cardRepository;
        this.branchRepository = branchRepository;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public String importEmployees(EmployeeRootImportDto employeeRootImportDto) {

        StringBuilder res = new StringBuilder();
        for (EmployeImportDto employeImportDto : employeeRootImportDto.getEmployees()) {
            if (!this.validationUtil.isValid(employeImportDto)) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Branch branch = this.branchRepository.findByName(employeImportDto.getBranch()).orElse(null);
            if (branch == null) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            EmployeeCard card = this.cardRepository.findByNumber(employeImportDto.getCard()).orElse(null);
            if (card == null) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if (this.employeeRepository.existsEmployeeByCard(card)) {
                res.append(TextUtil.ERROR_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Employee employee = this.mapper.map(employeImportDto, Employee.class);
            employee.setBranch(branch);
            employee.setCard(card);
            this.employeeRepository.saveAndFlush(employee);

            res.append(String.format(TextUtil.SUCCESS_MESSAGE_2PARAM, employee.getFirstName(), employee.getLastName()))
                    .append(System.lineSeparator());

        }
        return res.toString().trim();
    }

    @Override
    public ProductivEmployeeDto[] productiveEmployees() {
        List<Employee> withBranchProduct = this.employeeRepository.getWithBranchProduct();
        List<Employee> productiveEmployees = this.employeeRepository.getProductiveEmployees();
        ProductivEmployeeDto[] res=new ProductivEmployeeDto[withBranchProduct.size()];
        for (int i = 0; i < res.length; i++) {
            Employee employee = withBranchProduct.get(i);
            ProductivEmployeeDto productivEmployeeDto = this.mapper.map(employee, ProductivEmployeeDto.class);
            productivEmployeeDto.setFullName(String.format("%s %s",employee.getFirstName(),employee.getLastName()));
            productivEmployeeDto.setCard(employee.getCard().getNumber());
            res[i]=productivEmployeeDto;
        }
        return res;
    }
}
