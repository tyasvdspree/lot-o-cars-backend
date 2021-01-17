package nl.lotocars.rental.services;
import lombok.RequiredArgsConstructor;
import nl.lotocars.rental.entities.Location;
import nl.lotocars.rental.reposetories.LocationRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    public Location findOrCreateLocation(Location location){
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")//, "longitude", "latitude", "addressLine2", "version", "createdDate", "lastModified")
                .withMatcher("model", ignoreCase());
        Example<Location> example = Example.of(location, modelMatcher);
        return locationRepository.findOne(example).orElseGet(() -> locationRepository.save(location));
    }
}
