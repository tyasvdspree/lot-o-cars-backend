package nl.lotocars.rental.reposetories;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;

@Repository
public interface BaseRepository<T, ID extends Serializable> extends Repository {

    <S extends T> S save(S entity);

    T findOne(ID primaryKey);

    Collection<T> findAll();

    Long count();

    void delete(T entity);

    boolean exists(ID primaryKey);
}
