package ru.javalab.repositories;

import ru.javalab.models.entity.User;
import ru.javalab.models.enums.State;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {
    Optional<User> findUserByEmail(String email);

    void updateStateForUser(String email, State state);
}
