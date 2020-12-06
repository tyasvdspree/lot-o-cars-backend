package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByNumberPlate(String numberPlate);

    @Query("SELECT c FROM Car c WHERE " +
            "(:city is null OR c.location.city = :city) AND " +
            "(:make is null OR c.make = :make) AND " +
            "(:model is null OR c.model LIKE %:model%) AND " +
            "(:color is null OR c.color = :color) AND " +
            "(:fuel is null OR c.fuel = :fuel) AND " +
            "(:modelyear is null OR c.modelYear >= :modelyear) AND " +
            "(:doors = 0 OR c.doors = :doors) AND " +
            "(:seats = 0 OR c.seats = :seats) AND " +
            "(:bootspace = 0 OR c.bootSpaceInLiters >= :bootspace) AND " +
            "(:nonsmoking is null OR c.smokingIsAllowed = :nonsmoking)"
    )
    Collection<Car> findBySearchOptions(
            @Param("city") String city,
            @Param("make") String make,
            @Param("model") String model,
            @Param("color") String color,
            @Param("fuel") String fuel,
            @Param("modelyear") Date modelyear,
            @Param("doors") int doors,
            @Param("seats") int seats,
            @Param("bootspace") int bootspace,
            @Param("nonsmoking") Boolean nonsmoking
    );

}
