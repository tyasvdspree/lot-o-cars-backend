package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter

@Entity()
public class BrokerFeeRequest extends BaseEntity {
    private double proposedFee;
    private boolean isAccepted;
}
