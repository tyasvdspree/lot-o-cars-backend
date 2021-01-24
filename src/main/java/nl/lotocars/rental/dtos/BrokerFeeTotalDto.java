package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.AgreementStatus;

@Getter
@Setter
public class BrokerFeeTotalDto {

    public BrokerFeeTotalDto(
            Integer year,
            Integer month,
            AgreementStatus.agreemtStatus status,
            boolean payed,
            double total
    ) {
        this.year = year;
        this.month = month;
        this.status = status;
        this.payed = payed;
        this.total = total;
    }

    private Integer year;

    private Integer month;

    private boolean payed;

    private AgreementStatus.agreemtStatus status;

    private double total;

}
