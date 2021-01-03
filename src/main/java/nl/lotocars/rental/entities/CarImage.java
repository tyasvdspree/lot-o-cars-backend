package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "CarImage")
@Table(name = "CARIMAGE")
public class CarImage extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Lob
    private Byte[] carImage;

    private boolean isMainImage;

}
