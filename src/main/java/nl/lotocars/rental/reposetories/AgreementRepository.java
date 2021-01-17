package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, Long> {

    @Query("SELECT a FROM Agreement a WHERE " +
            "a.status != 2 AND " +
            "a.car.numberPlate = :numberPlate AND " +
            "a.startDate >= :fromDate"
    )
    Collection<Agreement> findBySearchOptions(
            @Param("numberPlate") String numberPlate,
            @Param("fromDate") Date fromDate
    );

    Collection<Agreement> findByRenter(User renter);

    Collection<Agreement> findByRentee(User rentee);

    @Query("SELECT a FROM Agreement a WHERE " +
            "a.status != 2 AND " +
            "a.rentee = :rentee AND " +
            "YEAR(a.startDate) BETWEEN :startYear AND :endYear"
    )
    Collection<Agreement> findByRenteeAndYears(
            @Param("rentee") User rentee,
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear
    );

}
