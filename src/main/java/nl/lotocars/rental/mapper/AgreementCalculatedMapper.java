package nl.lotocars.rental.mapper;

import nl.lotocars.rental.dtos.AgreementCalculatedDto;
import nl.lotocars.rental.dtos.CarDto;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.Car;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Mapper(componentModel = "spring")
public abstract class AgreementCalculatedMapper {

    public abstract AgreementCalculatedDto mapToDestination(Agreement source);

    public abstract CarDto mapToCarDestination(Car source);


    @AfterMapping
    void calculate(Agreement agreement, @MappingTarget AgreementCalculatedDto dto) {
        dto.setCarMake(agreement.getCar().getMake().toString());
        dto.setCarModel(agreement.getCar().getModel());
        dto.setNumberPlate(agreement.getCar().getNumberPlate());

        dto.setNumOfDays(TimeUnit.DAYS.convert(agreement.getEndDate().getTime() - agreement.getStartDate().getTime(), TimeUnit.MILLISECONDS) + 1);
        dto.setTotal(dto.getNumOfDays() * agreement.getRentPricePerHour());
        dto.setBrokerCosts(agreement.getBrokerFee() * dto.getTotal() * 0.01);
        dto.setProfit(dto.getTotal() - dto.getBrokerCosts());

        LocalDate startDate = agreement.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dto.setYear(startDate.getYear());
        dto.setMonth(startDate.getMonthValue());
    }
}
