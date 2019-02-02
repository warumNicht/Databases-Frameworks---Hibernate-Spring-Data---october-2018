package alararestaurant.service;

import alararestaurant.domain.dtos.importDtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.Constants;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final static  String EMPLOYEES_FILE_PATH="src\\main\\resources\\files\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
@Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionRepository positionRepository,
                               FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
    this.positionRepository = positionRepository;
    this.fileUtil = fileUtil;
    this.gson = gson;
    this.validationUtil = validationUtil;
    this.mapper = mapper;
}

    @Override
    public Boolean employeesAreImported() {
        // TODO : Implement me
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        // TODO : Implement me
        String fileContent = this.fileUtil.readFile(EMPLOYEES_FILE_PATH);
        return fileContent;
    }

    @Override
    public String importEmployees(String employees) {
        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);

        StringBuilder res=new StringBuilder();

        for (EmployeeImportDto employeeImportDto : employeeImportDtos) {
            if(!this.validationUtil.isValid(employeeImportDto)){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(!this.positionRepository.existsByName(employeeImportDto.getPosition())){
                Position position=new Position();
                position.setName(employeeImportDto.getPosition());
                this.positionRepository.saveAndFlush(position);
            }
            Position position = this.positionRepository.findByName(employeeImportDto.getPosition()).orElse(null);
            Employee employee = this.mapper.map(employeeImportDto, Employee.class);
            employee.setPosition(position);
           this.employeeRepository.saveAndFlush(employee);

            res.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,employee.getName()))
                    .append(System.lineSeparator());
        }

        // TODO : Implement me
        return res.toString().trim();
    }
}
