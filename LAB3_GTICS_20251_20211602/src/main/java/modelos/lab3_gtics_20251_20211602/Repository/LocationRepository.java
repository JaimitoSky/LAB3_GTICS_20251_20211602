package modelos.lab3_gtics_20251_20211602.Repository;

import jakarta.transaction.Transactional;
import modelos.lab3_gtics_20251_20211602.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query("SELECT DISTINCT l.city FROM Location l")
    List<String> obtenerCiudadesDisponibles();

    @Modifying
    @Transactional
    @Query("UPDATE Location l SET l.city = :city, l.postalCode = :postalCode WHERE l.locationId = :id")
    void actualizarCiudadYPostal(@Param("city") String city,
                                 @Param("postalCode") String postalCode,
                                 @Param("id") Integer id);
}
