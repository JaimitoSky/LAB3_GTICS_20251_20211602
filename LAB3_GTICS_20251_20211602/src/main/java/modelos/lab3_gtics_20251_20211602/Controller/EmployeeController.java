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
    @Autowired
    LocationRepository locationRepository;

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
    @GetMapping("/empleados/editar/{id}")
    public String editarEmpleado(@PathVariable("id") Integer id, Model model) {
        Employee emp = employeeRepository.findById(id).orElse(null);

        EmployeeLocationDTO dto = new EmployeeLocationDTO();
        dto.setEmployeeId(emp.getEmployeeId());
        dto.setFirstName(emp.getFirstName());
        dto.setLastName(emp.getLastName());
        dto.setEmail(emp.getEmail());
        dto.setJobTitle(emp.getJob().getJobTitle());
        dto.setCity(emp.getDepartment().getLocation().getCity());
        dto.setPostalCode(emp.getDepartment().getLocation().getPostalCode());
        dto.setLocationId(emp.getDepartment().getLocation().getLocationId());

        model.addAttribute("empDto", dto);
        model.addAttribute("ciudades", locationRepository.obtenerCiudadesDisponibles());
        return "editForm";
    }

    @PostMapping("/empleados/updateLocation")
    public String actualizarUbicacion(@ModelAttribute("empDto") EmployeeLocationDTO dto) {
        locationRepository.actualizarCiudadYPostal(dto.getCity(), dto.getPostalCode(), dto.getLocationId());
        return "redirect:/empleados";
    }
}

