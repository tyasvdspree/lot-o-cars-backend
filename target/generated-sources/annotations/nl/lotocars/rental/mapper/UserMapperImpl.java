package nl.lotocars.rental.mapper;

import javax.annotation.processing.Generated;
import nl.lotocars.rental.dtos.UserDto;
import nl.lotocars.rental.entities.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-07T15:51:34+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto mapToDestination(User source) {
        if ( source == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername( source.getUsername() );
        userDto.setLocation( source.getLocation() );

        userDto.setRoles( mapRoles(source) );

        return userDto;
    }

    @Override
    public User mapToSource(UserDto destination) {
        if ( destination == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( destination.getUsername() );
        user.setLocation( destination.getLocation() );

        return user;
    }
}
