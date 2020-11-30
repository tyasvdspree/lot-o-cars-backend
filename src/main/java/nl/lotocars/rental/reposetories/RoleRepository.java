package nl.lotocars.rental.reposetories;

import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public interface RoleRepository<T, ID extends Serializable> extends BaseRepository<T, ID>{

}
