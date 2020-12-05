package nl.lotocars.rental.mapper;

import javax.annotation.processing.Generated;
import nl.lotocars.rental.dtos.CarDto;
import nl.lotocars.rental.entities.Car;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-05T14:00:18+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
@Component
public class CarMapperImpl extends CarMapper {

    @Override
    public CarDto mapToDestination(Car source) {
        if ( source == null ) {
            return null;
        }

        CarDto carDto = new CarDto();

        carDto.setUser( source.getUser() );
        carDto.setLocation( source.getLocation() );
        carDto.setNumberPlate( source.getNumberPlate() );
        carDto.setCountryCode( source.getCountryCode() );
        carDto.setModelYear( source.getModelYear() );
        carDto.setMake( source.getMake() );
        carDto.setModel( source.getModel() );
        carDto.setBody( source.getBody() );
        carDto.setTransmission( source.getTransmission() );
        carDto.setFuel( source.getFuel() );
        carDto.setFuelUsage( source.getFuelUsage() );
        carDto.setDoors( source.getDoors() );
        carDto.setSeats( source.getSeats() );
        carDto.setBootSpaceInLiters( source.getBootSpaceInLiters() );
        carDto.setNavigation( source.isNavigation() );
        carDto.setAirco( source.isAirco() );
        carDto.setSmokingIsAllowed( source.isSmokingIsAllowed() );
        carDto.setActive( source.isActive() );

        return carDto;
    }

    @Override
    public Car mapToSource(CarDto destination) {
        if ( destination == null ) {
            return null;
        }

        Car car = new Car();

        car.setUser( destination.getUser() );
        car.setLocation( destination.getLocation() );
        car.setNumberPlate( destination.getNumberPlate() );
        car.setCountryCode( destination.getCountryCode() );
        car.setModelYear( destination.getModelYear() );
        car.setMake( destination.getMake() );
        car.setModel( destination.getModel() );
        car.setBody( destination.getBody() );
        car.setTransmission( destination.getTransmission() );
        car.setFuel( destination.getFuel() );
        car.setFuelUsage( destination.getFuelUsage() );
        car.setDoors( destination.getDoors() );
        car.setSeats( destination.getSeats() );
        car.setBootSpaceInLiters( destination.getBootSpaceInLiters() );
        car.setNavigation( destination.isNavigation() );
        car.setAirco( destination.isAirco() );
        car.setSmokingIsAllowed( destination.isSmokingIsAllowed() );
        car.setActive( destination.isActive() );

        return car;
    }
}
