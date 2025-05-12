package modelos.lab3_gtics_20251_20211602.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "regions")

@Getter
@Setter
public class Region {
    @Id
    @Column(name = "region_id")
    private BigDecimal regionId;

    @Column(name = "region_name")
    private String regionName;

    @OneToMany(mappedBy = "region")
    private List<Country> countries;
}
