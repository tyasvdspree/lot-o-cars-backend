package nl.lotocars.rental.mapper;

import nl.lotocars.rental.Enum.Role;
import nl.lotocars.rental.dtos.LocationDto;
import nl.lotocars.rental.dtos.UserDto;
import nl.lotocars.rental.entities.Location;
import nl.lotocars.rental.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract UserDto mapToDestination(User source);

    public abstract User mapToSource(UserDto destination);

    public abstract LocationDto mapToCarDestination(Location source);

    public abstract Location mapToCarSource(LocationDto destination);
}
