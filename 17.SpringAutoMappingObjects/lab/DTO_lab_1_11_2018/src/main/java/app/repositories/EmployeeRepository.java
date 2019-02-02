package app.repositories;

import app.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    List<Employee> findAllByBirthDateBeforeOrderBySalaryDesc(LocalDate birthDate);
}
