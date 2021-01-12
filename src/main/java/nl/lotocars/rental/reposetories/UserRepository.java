package nl.lotocars.rental.reposetories;

import nl.lotocars.rental.entities.Agreement;
import nl.lotocars.rental.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);
    Optional<User> findById(Long id);

    @Query("SELECT a FROM user a WHERE a.emailaddress = :emailaddress")
    Collection<User> findByUserEmailAddress(@Param("emailaddress") String emailaddress);



}
