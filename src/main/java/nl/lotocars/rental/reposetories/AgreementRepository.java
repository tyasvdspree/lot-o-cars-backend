package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.dtos.BrokerFeeTotalDto;
import nl.lotocars.rental.dtos.KeyValueDto;
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
            "a.renter != a.rentee AND " +
            "a.rentee = :rentee AND " +
            "YEAR(a.startDate) BETWEEN :startYear AND :endYear"
    )
    Collection<Agreement> findByRenteeAndYears(
            @Param("rentee") User rentee,
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear
    );


    @Query("SELECT new nl.lotocars.rental.dtos.BrokerFeeTotalDto(" +
            "YEAR(a.startDate), " +
            "MONTH(a.startDate), " +
            "a.status, " +
            "a.isPayed, " +
            "SUM(((a.endDate - a.startDate) / 1000000 + 1) * a.rentPricePerHour * a.brokerFee * 0.01) ) " +
           "FROM " +
            "Agreement a " +
           "WHERE " +
            "a.status != 2 AND " +
            "YEAR(a.startDate) BETWEEN :startYear AND :endYear " +
           "GROUP BY " +
            "YEAR(a.startDate), " +
            "MONTH(a.startDate), " +
            "a.status, " +
            "a.isPayed"
    )
    Collection<BrokerFeeTotalDto> getBrokerFeeTotals(
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear
    );


    @Query("SELECT new nl.lotocars.rental.dtos.KeyValueDto('Rentees', COUNT(DISTINCT a.rentee.id)) FROM Agreement a"
    )
    KeyValueDto getGeneralRenteeCount();

    @Query("SELECT new nl.lotocars.rental.dtos.KeyValueDto('Renters', COUNT(DISTINCT a.renter.id)) FROM Agreement a"
    )
    KeyValueDto getGeneralRenterCount();

    @Query("SELECT new nl.lotocars.rental.dtos.KeyValueDto(YEAR(a.startDate), COUNT(*)) " +
            "FROM Agreement a WHERE a.status = 2 GROUP BY YEAR(a.startDate)"
    )
    Collection<KeyValueDto> getGeneralAverageCancellationsPerYear();

    @Query("SELECT new nl.lotocars.rental.dtos.KeyValueDto(YEAR(a.startDate), COUNT(*)) " +
            "FROM Agreement a WHERE a.status != 2 GROUP BY YEAR(a.startDate)"
    )
    Collection<KeyValueDto> getGeneralAverageAgreementsPerYear();

    @Query("SELECT new nl.lotocars.rental.dtos.KeyValueDto(YEAR(a.startDate), COUNT(DISTINCT a.car.id)) " +
            "FROM Agreement a WHERE a.status != 2 GROUP BY YEAR(a.startDate)"
    )
    Collection<KeyValueDto> getGeneralAverageInvolvedCarsPerYear();

}
