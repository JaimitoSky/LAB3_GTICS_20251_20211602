package modelos.lab3_gtics_20251_20211602.Controller;

import modelos.lab3_gtics_20251_20211602.Entity.Employee;
import modelos.lab3_gtics_20251_20211602.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/empleados")
    public String listarEmpleados(
            @RequestParam(required = false) String filtro,
            Model model) {

        List<Employee> empleados;
        if (filtro != null && !filtro.isEmpty()) {
            empleados = employeeRepository.findByFirstNameContainingIgnoreCaseOrDepartment_DepartmentNameContainingIgnoreCase(filtro, filtro);
        } else {
            empleados = employeeRepository.findAll();
        }

        model.addAttribute("listaEmpleados", empleados);
        model.addAttribute("filtro", filtro);
        return "employeeList";
    }
}

