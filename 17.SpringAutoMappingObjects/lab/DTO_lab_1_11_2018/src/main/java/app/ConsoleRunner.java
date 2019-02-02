package app;

import app.DTO.EmployeeDto;
import app.DTO.EmployeeManagerDto;
import app.DTO.ManagerDto;
import app.models.Address;
import app.models.Employee;
import app.repositories.AddressRepository;
import app.repositories.EmployeeRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private AddressRepository addressRepository;
    private EmployeeRepository employeeRepository;

@Autowired
    public ConsoleRunner(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }
    @Override
    public void run(String... args) throws Exception {

//        Address address=new Address("Sofia","Bulgarien");
//        this.addressRepository.save(address);
//        Optional<Employee> byId = this.employeeRepository.findById(1L);
//        if(byId.isPresent()){
//            Employee employee = byId.get();
//
//            ModelMapper mapper=new ModelMapper();
//            ManagerDto managerDto = mapper.map(employee, ManagerDto.class);
//
//
//            System.out.println(managerDto.getFirstName());
//            for (EmployeeDto employeeDto : managerDto.getServants()) {
//                System.out.printf("- %s %s %.2f%n",employeeDto.getFirstName(),
//                        employeeDto.getLastName(),employeeDto.getSalary());
//            }
//        }
        List<Employee> allByBirthDateBefore = this.employeeRepository
                .findAllByBirthDateBeforeOrderBySalaryDesc(LocalDate.parse("1990-01-23"));

        ModelMapper mapper=new ModelMapper();
        Converter<String,String> stringConverter=new AbstractConverter<String, String>() {
            @Override
            protected String convert(String employee) {
                return employee==null? "[no manager]":employee;
            }
        };

        PropertyMap<Employee,EmployeeManagerDto> propertyMap=new PropertyMap<Employee, EmployeeManagerDto>() {
            @Override
            protected void configure() {
                using(stringConverter).map().setManager(source.getManager().getFirstName());
            }
        };

        mapper.addMappings(propertyMap);

        for (Employee employee : allByBirthDateBefore) {
            EmployeeManagerDto employeeManagerDto = mapper.map(employee, EmployeeManagerDto.class);
            System.out.printf("%s %s %.2f - %s%n",employeeManagerDto.getFirstName(),
                    employeeManagerDto.getLastName(),
                    employeeManagerDto.getSalary(),
                    employeeManagerDto.getManager());
        }

    }
}
