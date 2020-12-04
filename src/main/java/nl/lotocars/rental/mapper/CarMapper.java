package nl.lotocars.rental.mapper;

import nl.lotocars.rental.dtos.CarDto;
import nl.lotocars.rental.entities.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CarMapper{

    public abstract CarDto mapToDestination(Car source);

    public abstract Car mapToSource(CarDto destination);
}