package co.edu.javeriana.discovery.pica.location.repository;

import co.edu.javeriana.discovery.pica.location.repository.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}
