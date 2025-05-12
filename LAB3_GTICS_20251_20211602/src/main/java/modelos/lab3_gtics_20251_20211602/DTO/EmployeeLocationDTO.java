package modelos.lab3_gtics_20251_20211602.DTO;

import lombok.Data;

@Data
public class EmployeeLocationDTO {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String city;
    private String postalCode;
    private Integer locationId;

}