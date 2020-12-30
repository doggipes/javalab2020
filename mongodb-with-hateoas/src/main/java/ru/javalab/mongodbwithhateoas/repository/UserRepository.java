package ru.javalab.mongodbwithhateoas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.javalab.mongodbwithhateoas.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    @RestResource(path = "byEmailAndPass")
    @Query(value = "{ email : '?0', $or: [{password: '?1'}] }")
    User findByEmailAndPassword(String email, String password);
}
