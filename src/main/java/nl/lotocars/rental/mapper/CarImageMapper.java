package nl.lotocars.rental.mapper;

import nl.lotocars.rental.dtos.CarImageDto;
import nl.lotocars.rental.entities.CarImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CarImageMapper {
    public abstract CarImageDto mapToDestination(CarImage source);

    public abstract CarImage mapToSource(CarImageDto destination);
}
