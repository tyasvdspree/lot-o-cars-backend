package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.Enum.*;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;
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

    @Query("SELECT c FROM Car c WHERE c.isActive = 1 AND " +
            "(:city is null OR c.location.city = :city) AND " +
            "((:pickupdate is null AND :dropoffdate is null) OR (NOT EXISTS (SELECT a FROM Agreement a WHERE a.car.id = c.id AND a.status != :statusCanceled AND " +
            "((:pickupdate BETWEEN a.startDate AND a.endDate)  OR  (:dropoffdate BETWEEN a.startDate AND a.endDate) OR " +
            "((a.startDate BETWEEN :pickupdate AND :dropoffdate)  AND  (a.endDate BETWEEN :pickupdate AND :dropoffdate)))))) AND " +
            "(:make is null OR c.make = :make) AND " +
            "(:model is null OR c.model LIKE %:model%) AND " +
            "(:color is null OR c.color = :color) AND " +
            "(:transmission is null OR c.transmission = :transmission) AND " +
            "(:fuel is null OR c.fuel = :fuel) AND " +
            "(:modelyear = 0 OR YEAR(c.modelYear) = :modelyear) AND " +
            "(:doors = 0 OR c.doors = :doors) AND " +
            "(:seats = 0 OR c.seats = :seats) AND " +
            "(:bootspace = 0 OR c.bootSpaceInLiters >= :bootspace) AND " +
            "(:nonsmoking = -1 OR c.smokingIsAllowed != :nonsmoking)"
    )
    Collection<Car> findBySearchOptions(
            @Param("city") String city,
            @Param("pickupdate") Date pickupdate,
            @Param("dropoffdate") Date dropoffdate,
            @Param("make") Make.make make,
            @Param("model") String model,
            @Param("color") Color.color color,
            @Param("transmission") Transmission.transmission transmission,
            @Param("fuel") Fuel.fuel fuel,
            @Param("modelyear") int modelyear,
            @Param("doors") int doors,
            @Param("seats") int seats,
            @Param("bootspace") int bootspace,
            @Param("nonsmoking") int nonsmoking,
            @Param("statusCanceled")AgreementStatus.agreemtStatus statusCanceled
            );

    Collection<Car> findByUser(User owner);

}
