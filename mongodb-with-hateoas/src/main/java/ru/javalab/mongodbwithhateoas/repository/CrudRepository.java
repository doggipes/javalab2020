package ru.javalab.mongodbwithhateoas.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository <T, ID>  {
    T save(T entity);

    List<T> findAll();

    void delete(ID id);

    Optional<T> findById(ID id);
}
