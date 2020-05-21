package ru.javalab.repositories;

import ru.javalab.models.entity.User;
import ru.javalab.models.entity.VerificationToken;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Long, VerificationToken>{
    Optional<VerificationToken> findEntityByToken(String token);

    Optional<VerificationToken> findTokenByUser(User user);
}
