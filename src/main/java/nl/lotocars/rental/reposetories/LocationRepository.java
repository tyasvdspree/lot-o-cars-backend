package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}