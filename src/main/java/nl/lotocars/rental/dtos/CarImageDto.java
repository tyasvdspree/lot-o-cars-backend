package nl.lotocars.rental.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarImageDto {
    private Long id;
    private Byte[] carImage;
    private boolean isMainImage;
}
