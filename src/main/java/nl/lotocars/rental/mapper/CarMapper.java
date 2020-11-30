package nl.lotocars.rental.mapper;

import nl.lotocars.rental.dtos.CarDto;
import nl.lotocars.rental.entities.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class CarMapper{

    @Mapping(target = "roles", expression = "java(mapRoles(source))")
    public abstract CarDto mapToDestination(Car source);

    @Mapping(target = "roles", ignore = true)
    public abstract Car mapToSource(CarDto destination);
}