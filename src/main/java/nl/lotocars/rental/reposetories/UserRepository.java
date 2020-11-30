package nl.lotocars.rental.reposetories;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UserRepository <T, ID extends Serializable> extends BaseRepository {
    Optional<T> findByName(String name);
    Optional<T> findById(Long id);
}
