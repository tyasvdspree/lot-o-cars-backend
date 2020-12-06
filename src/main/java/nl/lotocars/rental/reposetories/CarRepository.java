package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByNumberPlate(String numberPlate);

    @Query("SELECT c FROM Car c WHERE " +
            "(:city is null or c.location.city = :city) AND " +
            "(:make is null or c.make = :make) AND " +
            "(:color is null or c.color = :color)")
    Collection<Car> findBySearchOptions(
            @Param("city") String city,
            @Param("make") String make,
            @Param("color") String color
    );

}
