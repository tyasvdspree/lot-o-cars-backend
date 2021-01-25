package nl.lotocars.rental.mapper;

import nl.lotocars.rental.dtos.AgreementDto;
import nl.lotocars.rental.dtos.CarDto;
import nl.lotocars.rental.dtos.UserDto;
import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.Car;
import nl.lotocars.rental.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AgreementMapper {

    public abstract AgreementDto mapToDestination(Agreement source);

    public abstract Agreement mapToSource(AgreementDto destination);

    public abstract CarDto mapToCarDestination(Car source);

    public abstract Car mapToCarSource(CarDto destination);

    @Mapping(target = "roles", expression = "java(mapRoles(source))")
    public abstract UserDto mapToUserDestination(User source);

    @Mapping(target = "roles", ignore = true)
    public abstract User mapToUserSource(UserDto destination);

    @Named("userTransformation")
    protected Collection<String> mapRoles (User source) {
        return source.getRoles().stream().map(x -> x.getName()).collect(Collectors.toList());
    }
}

