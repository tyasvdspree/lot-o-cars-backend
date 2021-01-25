package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.entities.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface CarImageRepository extends JpaRepository<CarImage, Long> {

    @Query("SELECT ci.id FROM CarImage ci WHERE ci.car.numberPlate = :numberPlate")
    Collection<Long> findIdsByNumberPlate(@Param("numberPlate") String numberPlate);

    @Query("SELECT min(ci.id) FROM CarImage ci WHERE ci.car.numberPlate = :numberPlate")
    Long findImageIdByCarId(@Param("numberPlate") String numberPlate);

}
