package ru.javalab.rabbitmq.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, V> {
    V save(V entity);
    void delete(ID id);
    Optional<V> find(ID id);
    List<V> findAll();
}
