package modelos.lab3_gtics_20251_20211602.Repository;

import modelos.lab3_gtics_20251_20211602.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstNameContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCase(String nombre, String departamento);
}
