package modelos.lab3_gtics_20251_20211602.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class JobHistoryId implements Serializable {
    private Integer employeeId;
    private java.sql.Date startDate;
}