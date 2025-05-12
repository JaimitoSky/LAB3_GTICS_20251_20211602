package modelos.lab3_gtics_20251_20211602.Controller;

import modelos.lab3_gtics_20251_20211602.Entity.Employee;
import modelos.lab3_gtics_20251_20211602.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReportController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String verReportes(@RequestParam(value = "filtro", required = false) String filtro, Model model) {

        List<Employee> empleados = (filtro != null && !filtro.isEmpty())
                ? employeeRepository.findByFirstNameContainingIgnoreCase(filtro)

                : employeeRepository.findAll();

        model.addAttribute("empleados", empleados);
        model.addAttribute("salarioMax", employeeRepository.obtenerSalarioMaximo());
        model.addAttribute("salarioMin", employeeRepository.obtenerSalarioMinimo());
        model.addAttribute("promedios", employeeRepository.obtenerPromedioSalarioPorPuesto());
        model.addAttribute("nombreMax", employeeRepository.obtenerEmpleadoConSalarioMaximo());
        model.addAttribute("filtro", filtro);

        return "reportList";
    }
}