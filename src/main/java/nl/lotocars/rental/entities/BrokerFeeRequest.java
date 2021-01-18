package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;
import nl.lotocars.rental.Enum.AgreementStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity()
public class BrokerFeeRequest extends BaseEntity {
    @NotEmpty
    private double originalFee;
    @NotEmpty
    private double proposedFee;
    @NotEmpty
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private AgreementStatus.agreemtStatus status;
    private String reason;
}
