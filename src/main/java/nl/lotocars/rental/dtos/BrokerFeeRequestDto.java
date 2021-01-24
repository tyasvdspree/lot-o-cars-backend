package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.AgreementStatus;

@Getter
@Setter
public class BrokerFeeRequestDto {
    private long id;
    private double originalFee;
    private double proposedFee;
    private UserDto user;
    private AgreementStatus.agreemtStatus status;
    private String reason;
}
