package nl.lotocars.rental.mapper;

import nl.lotocars.rental.dtos.BrokerFeeRequestDto;
import nl.lotocars.rental.dtos.UserDto;
import nl.lotocars.rental.entities.BrokerFeeRequest;
import nl.lotocars.rental.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class BrokerFeeMapper {

    public abstract BrokerFeeRequestDto mapToDestination(BrokerFeeRequest source);

    public abstract BrokerFeeRequest mapToSource(BrokerFeeRequestDto destination);

    @Mapping(target = "roles", expression = "java(mapRoles(source))")
    public abstract UserDto mapToUserDestination(User source);

    @Mapping(target = "roles", ignore = true)
    public abstract User mapToUserSource(UserDto destination);

    @Named("userTransformation")
    protected Collection<String> mapRoles (User source) {
        return source.getRoles().stream().map(x -> x.getName()).collect(Collectors.toList());
    }
}
