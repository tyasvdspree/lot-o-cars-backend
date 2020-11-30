package nl.lotocars.rental.reposetories;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface CarRepository <T, ID extends Serializable> extends BaseRepository {

    Optional<T> findByNumberPlate(String numberPlate);
}
