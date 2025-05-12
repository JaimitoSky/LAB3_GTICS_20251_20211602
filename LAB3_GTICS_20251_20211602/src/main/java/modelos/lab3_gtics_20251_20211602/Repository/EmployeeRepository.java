package modelos.lab3_gtics_20251_20211602.Repository;

import modelos.lab3_gtics_20251_20211602.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstNameContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCase(String nombre, String departamento);
    List<Employee> findByFirstNameContainingIgnoreCase(String filtro);

    @Query("SELECT MAX(e.salary) FROM Employee e")
    Double obtenerSalarioMaximo();

    @Query("SELECT MIN(e.salary) FROM Employee e")
    Double obtenerSalarioMinimo();

    @Query("SELECT e.job.jobTitle, ROUND(AVG(e.salary), 2) FROM Employee e GROUP BY e.job.jobTitle")
    List<Object[]> obtenerPromedioSalarioPorPuesto();

    @Query("SELECT CONCAT(e.firstName, ' ', e.lastName) FROM Employee e WHERE e.salary = (SELECT MAX(e2.salary) FROM Employee e2)")
    String obtenerEmpleadoConSalarioMaximo();
}

