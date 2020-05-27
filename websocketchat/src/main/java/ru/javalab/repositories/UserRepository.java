package ru.javalab.repositories;

import ru.javalab.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {
    Optional<User> findUserByEmail(String email);
}
