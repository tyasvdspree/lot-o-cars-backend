package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.AgreementStatus;

@Getter
@Setter
public class BrokerFeeStatusDto {

    private long id;

    private AgreementStatus.agreemtStatus status;

    private String reason;
}
