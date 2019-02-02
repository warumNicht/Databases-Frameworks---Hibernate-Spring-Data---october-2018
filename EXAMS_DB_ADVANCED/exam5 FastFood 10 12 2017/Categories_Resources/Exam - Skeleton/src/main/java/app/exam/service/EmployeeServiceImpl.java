package app.exam.service;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Position;
import app.exam.parser.interfaces.ModelParser;
import app.exam.parser.interfaces.ValidationUtil;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.PositionRepository;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private ValidationUtil validationUtil;
    private ModelParser mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PositionRepository positionRepository,
                               ValidationUtil validationUtil, ModelParser mapper) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public void create(EmployeeJSONImportDTO importDTO) {
        Employee employee = this.mapper.convert(importDTO, Employee.class);
        if (!this.positionRepository.existsByName(importDTO.getPosition())) {
            Position position = new Position();
            position.setName(importDTO.getPosition());
            this.positionRepository.saveAndFlush(position);
        }
        Position position = this.positionRepository.getByName(importDTO.getPosition());
        employee.setPosition(position);
        this.employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void createMany(EmployeeJSONImportDTO[] importDTO) {

    }
}
