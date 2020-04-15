package ru.javalab.hw2.repositories;

import ru.javalab.hw2.models.User;

import java.util.Optional;

public interface RegisterReposiroty extends CrudRepository<Long, User> {
    Optional<User> findUserByEmail(String email);

    void confirmedEmail(String email);
}
